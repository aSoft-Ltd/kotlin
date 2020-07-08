package tz.co.asoft.paging.dao

import tz.co.asoft.persist.dao.IDao
import tz.co.asoft.persist.model.Entity

interface IPagedDao<K : Any, D : Entity> : IDao<D>, Pageable<K, D>