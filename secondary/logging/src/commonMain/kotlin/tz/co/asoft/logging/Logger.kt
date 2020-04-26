package tz.co.asoft.logging

import tz.co.asoft.logging.tools.Cause
import tz.co.asoft.persist.repo.IRepo

expect open class Logger(source: String = "anonymous", repo: IRepo<Log>? = null) {
    protected val source: String
    protected val repo: IRepo<Log>?
    var tag: String
    fun d(msg: String)
    fun e(msg: String, c: Cause? = null)
    fun e(c: Cause? = null)
    fun f(msg: String, c: Cause? = null)
    fun f(c: Cause? = null)
    fun w(msg: String)
    fun i(msg: String)
    fun obj(vararg o: Any?)
    fun obj(o: Any?)
}