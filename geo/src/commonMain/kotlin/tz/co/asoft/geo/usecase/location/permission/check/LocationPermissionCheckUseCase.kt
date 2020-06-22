package tz.co.asoft.geo.usecase.location.permission.check

import tz.co.asoft.platform.core.Ctx
import tz.co.asoft.platform.core.Manifest
import tz.co.asoft.platform.core.usecase.permission.check.IPermissionCheckUseCase
import tz.co.asoft.platform.core.usecase.permission.check.PermissionCheckUseCase

fun LocationPermissionCheckUseCase(ctx: Ctx): IPermissionCheckUseCase = PermissionCheckUseCase(ctx, Manifest.permission.ACCESS_FINE_LOCATION)