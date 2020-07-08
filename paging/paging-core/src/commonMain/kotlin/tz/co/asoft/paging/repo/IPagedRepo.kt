package tz.co.asoft.paging.repo

import tz.co.asoft.paging.dao.IPagedDao
import tz.co.asoft.persist.model.Entity

interface IPagedRepo<K : Any, D : Entity> : IPagedDao<K, D>