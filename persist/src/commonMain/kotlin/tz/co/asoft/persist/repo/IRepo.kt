package tz.co.asoft.persist.repo

import tz.co.asoft.persist.dao.IDao
import tz.co.asoft.persist.model.Entity

interface IRepo<T : Entity> : IDao<T>