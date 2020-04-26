package tz.co.asoft.platform.core.usecase.permission.check

import tz.co.asoft.platform.core.Ctx

@Deprecated("Do not use this")
expect open class PermissionCheckUseCase(ctx: Ctx, perm: String) : IPermissionCheckUseCase