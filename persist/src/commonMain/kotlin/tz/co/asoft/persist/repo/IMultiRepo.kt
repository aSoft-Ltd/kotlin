package tz.co.asoft.persist.repo

import tz.co.asoft.persist.dao.IMultiDao
import tz.co.asoft.persist.model.Entity

interface IMultiRepo<T : Entity> : IRepo<T>, IMultiDao<T>