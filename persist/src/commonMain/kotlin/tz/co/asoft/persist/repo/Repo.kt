package tz.co.asoft.persist.repo

import tz.co.asoft.persist.dao.IDao
import tz.co.asoft.persist.model.Entity

open class Repo<T : Entity>(private val dao: IDao<T>) : IRepo<T>, IDao<T> by dao