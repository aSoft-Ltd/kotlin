package tz.co.asoft.places.data.dao

import tz.co.asoft.firebase.firestore.FirebaseFirestore
import tz.co.asoft.firebase.firestore.collection
import tz.co.asoft.firebase.firestore.query.fetch
import tz.co.asoft.firebase.firestore.query.get
import tz.co.asoft.firebase.firestore.query.where
import tz.co.asoft.firebase.firestore.snapshot.documents
import tz.co.asoft.firebase.firestore.snapshot.forEach
import tz.co.asoft.firebase.firestore.snapshot.toObject
import tz.co.asoft.persist.tools.Cause
import tz.co.asoft.places.country.Country
import tz.co.asoft.places.disctrict.District
import tz.co.asoft.places.region.Region
import tz.co.asoft.places.street.Street
import tz.co.asoft.places.ward.Ward

class PlacesFirebaseDao(override val firestore: FirebaseFirestore) : IPlacesDao {
    override val collectionName = "countries"
    override val serializer = Country.serializer()

    override suspend fun loadCountryByCode(code: String): Country? {
        val qs = firestore.collection("countries").where("alpha2Code", "==", code).fetch()
        return qs.documents.getOrNull(0)?.toObject(Country.serializer())
    }

    override suspend fun loadRegionsInCountryWithCode(code: String): Array<Region> {
        val country = loadCountryByCode(code)
                ?: throw Cause("Couldn't load country with code $code")
        return firestore.collection("countries/${country.name}/regions").fetch().documents.mapNotNull {
            it.toObject(Region.serializer())?.apply { countryName = country.name ?: "" }
        }.toTypedArray()
    }

    override suspend fun loadDistrictsIn(countryCode: String, regionName: String): Array<District> {
        var districts = arrayOf<District>()
        val region = loadRegionsInCountryWithCode(countryCode).firstOrNull {
            it.name == regionName
        }
        if (region != null) {
            if (region.districts.isNotEmpty()) {
                return region.districts.toTypedArray()
            }
            val path = "countries/${region.countryName}/regions/${region.name}/districts"
            firestore.collection(path).get { qs ->
                qs.forEach { doc ->
                    val dis = doc.toObject(District.serializer())?.also { d ->
                        d.regionName = region.name
                        d.countryName = region.countryName
                    } ?: return@forEach
                    districts += dis
                }
            }
            region.districts = districts.toMutableList()
        }
        return districts
    }

    override suspend fun loadWardsIn(countryCode: String, regionName: String, districtName: String): Array<Ward> {
        var wards = arrayOf<Ward>()
        val district = loadDistrictsIn(countryCode, regionName).firstOrNull {
            it.name == districtName
        }
        if (district != null) {
            if (district.wards.isNotEmpty()) {
                return district.wards.toTypedArray()
            }
            val path =
                    "countries/${district.countryName}/regions/${district.regionName}/districts/${district.name}/wards"
            firestore.collection(path).get { qs ->
                qs.forEach { doc ->
                    val ward = doc.toObject(Ward.serializer())?.also { w ->
                        w.countryName = district.countryName
                        w.regionName = district.regionName
                        w.districtName = district.name
                    } ?: return@forEach
                    wards += ward
                }
            }
            district.wards = wards.toMutableList()
        }
        return wards
    }

    override suspend fun loadStreetsIn(
            countryCode: String,
            regionName: String,
            districtName: String,
            wardName: String
    ): Array<Street> {
        var streets = arrayOf<Street>()
        val ward = loadWardsIn(countryCode, regionName, districtName).firstOrNull {
            it.name == wardName
        }

        if (ward != null) {
            if (ward.streets.isNotEmpty()) {
                return ward.streets.toTypedArray()
            }
            val path =
                    "countries/${ward.countryName}/regions/${ward.regionName}/districts/${ward.districtName}/wards/${ward.name}/streets"
            firestore.collection(path).get { qs ->
                qs.forEach { doc ->
                    val street = doc.toObject(Street.serializer()) ?: return@forEach
                    streets += street
                }
            }
            ward.streets = streets.toMutableList()
        }
        return streets
    }

    override suspend fun all(): List<Country> {
        val countries = mutableListOf<Country>()
        firestore.collection("countries").get { qs ->
            qs.forEach {
                val c = it.toObject(Country.serializer()) ?: return@forEach
                countries += c
            }
        }
        return countries
    }
}