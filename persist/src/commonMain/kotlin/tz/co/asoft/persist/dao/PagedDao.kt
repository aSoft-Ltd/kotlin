package tz.co.asoft.persist.dao

import tz.co.asoft.persist.model.Entity
import tz.co.asoft.persist.paging.PagingSource

abstract class PagedDao<K : Any, T : Entity> : PagingSource<K, T>(), IDao<T>