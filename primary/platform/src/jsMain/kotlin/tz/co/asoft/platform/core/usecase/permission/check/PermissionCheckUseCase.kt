package tz.co.asoft.platform.core.usecase.permission.check

import tz.co.asoft.platform.core.Ctx

actual open class PermissionCheckUseCase actual constructor(ctx: Ctx, perm: String) : IPermissionCheckUseCase {
    override fun isPermitted(): Boolean = true
}