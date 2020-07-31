package tz.co.asoft

expect open class Logger(source: String = "anonymous", repo: IRepo<Log>? = null) {
    protected val source: String
    protected val repo: IRepo<Log>?
    var tag: String
    fun d(msg: String)
    fun e(msg: String, c: Exception? = null)
    fun e(c: Exception? = null)
    fun f(msg: String, c: Exception? = null)
    fun f(c: Exception? = null)
    fun w(msg: String)
    fun i(msg: String)
    fun obj(vararg o: Any?)
    fun obj(o: Any?)
}