package tz.co.asoft.platform.core.usecase.permission.request

import tz.co.asoft.platform.core.usecase.permission.check.IPermissionCheckUseCase

@Deprecated("Do not use this")
expect class PermissionRequestUseCase(
        permCheckUC: IPermissionCheckUseCase,
        perms: List<String>,
        title: String,
        msg: String
) : IPermissionRequestUseCase