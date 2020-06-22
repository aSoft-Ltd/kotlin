package tz.co.asoft.places.data.repo

import tz.co.asoft.places.data.dao.IPlacesDao

class PlacesRepo(private val dao: IPlacesDao) : IPlacesRepo, IPlacesDao by dao