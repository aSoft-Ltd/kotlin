package tz.co.asoft

expect open class Logger(source: String = "anonymous", repo: IRepo<Log>? = null) {
    protected val source: String
    protected val repo: IRepo<Log>?
    var tag: String
    fun d(msg: String)
    fun e(msg: String, c: Throwable? = null)
    fun e(c: Throwable? = null)
    fun f(msg: String, c: Throwable? = null)
    fun f(c: Throwable? = null)
    fun w(msg: String)
    fun i(msg: String)
    fun obj(vararg o: Any?)
    fun obj(o: Any?)
}