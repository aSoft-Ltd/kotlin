package tz.co.asoft.places.data.viewmodel

import tz.co.asoft.places.data.repo.IPlacesRepo

open class TZPlacesViewModel(private val placesRepo: IPlacesRepo) {
    suspend fun nationalities() = placesRepo.all()?.mapNotNull {
        it.demonym
    }?.filter { it.isNotBlank() }

    suspend fun regions() = placesRepo.loadRegionsInCountryWithCode("TZ").map { it.name }

    suspend fun districts(regionName: String): List<String> {
        return placesRepo.loadDistrictsIn("TZ", regionName).map { it.name }
    }

    suspend fun wards(regionName: String, districtName: String): List<String> {
        return placesRepo.loadWardsIn("TZ", regionName, districtName).map { it.name }
    }

    suspend fun streets(regionName: String, districtName: String, wardName: String): List<String> {
        return placesRepo.loadStreetsIn("TZ", regionName, districtName, wardName).map { it.name }
    }
}