package tz.co.asoft.persist.repo

import tz.co.asoft.persist.dao.IMultiDao
import tz.co.asoft.persist.model.Entity

open class MultiRepo<T : Entity>(private val dao: IMultiDao<T>) : IMultiRepo<T>, IMultiDao<T> by dao