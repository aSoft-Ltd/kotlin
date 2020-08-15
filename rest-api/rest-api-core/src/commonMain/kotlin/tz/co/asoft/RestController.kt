package tz.co.asoft

open class RestController<T : Entity>(private val repo: IRepo<T>) : IRestController<T>, IRepo<T> by repo