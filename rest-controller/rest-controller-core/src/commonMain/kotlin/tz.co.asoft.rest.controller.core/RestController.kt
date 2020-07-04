package tz.co.asoft.rest.controller.core

import tz.co.asoft.persist.model.Entity
import tz.co.asoft.persist.repo.IRepo

open class RestController<T : Entity>(private val repo: IRepo<T>) : IRestController<T>, IRepo<T> by repo