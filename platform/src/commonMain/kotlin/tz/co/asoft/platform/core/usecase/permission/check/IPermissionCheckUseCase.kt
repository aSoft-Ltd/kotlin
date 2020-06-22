package tz.co.asoft.platform.core.usecase.permission.check

@Deprecated("Do not use this")
interface IPermissionCheckUseCase {
    val permitted get() = isPermitted()
    val notPermitted get() = !isPermitted()
    fun isPermitted(): Boolean
}