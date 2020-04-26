package tz.co.asoft.places.data.dao

import tz.co.asoft.firebase.firestore.dao.IFirebaseDao
import tz.co.asoft.persist.dao.IDao
import tz.co.asoft.places.country.Country
import tz.co.asoft.places.disctrict.District
import tz.co.asoft.places.region.Region
import tz.co.asoft.places.street.Street
import tz.co.asoft.places.ward.Ward

interface IPlacesDao : IFirebaseDao<Country> {
    suspend fun loadCountryByCode(code: String): Country?
    suspend fun loadRegionsInCountryWithCode(code: String): Array<Region>
    suspend fun loadDistrictsIn(countryCode: String, regionName: String): Array<District>
    suspend fun loadWardsIn(countryCode: String, regionName: String, districtName: String): Array<Ward>
    suspend fun loadStreetsIn(countryCode: String, regionName: String, districtName: String, wardName: String): Array<Street>
}