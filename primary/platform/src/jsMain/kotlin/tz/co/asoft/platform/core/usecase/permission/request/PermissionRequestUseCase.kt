package tz.co.asoft.platform.core.usecase.permission.request

import tz.co.asoft.platform.core.Activity
import tz.co.asoft.platform.core.usecase.permission.check.IPermissionCheckUseCase

actual class PermissionRequestUseCase actual constructor(permCheckUC: IPermissionCheckUseCase, perms: List<String>, title: String, msg: String) : IPermissionRequestUseCase {
    override fun invoke(a: Activity, requestCode: Int) {

    }
}