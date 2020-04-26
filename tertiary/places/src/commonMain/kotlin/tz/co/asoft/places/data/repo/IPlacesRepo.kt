package tz.co.asoft.places.data.repo

import tz.co.asoft.persist.repo.IRepo
import tz.co.asoft.persist.repo.Repo
import tz.co.asoft.places.country.Country
import tz.co.asoft.places.data.dao.IPlacesDao

interface IPlacesRepo : IRepo<Country>, IPlacesDao