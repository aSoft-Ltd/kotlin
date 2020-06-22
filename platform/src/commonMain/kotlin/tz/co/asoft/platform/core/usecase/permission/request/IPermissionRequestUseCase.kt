package tz.co.asoft.platform.core.usecase.permission.request

import tz.co.asoft.platform.core.Activity

@Deprecated("Do not use this")
interface IPermissionRequestUseCase {
    operator fun invoke(a: Activity, requestCode: Int)
}