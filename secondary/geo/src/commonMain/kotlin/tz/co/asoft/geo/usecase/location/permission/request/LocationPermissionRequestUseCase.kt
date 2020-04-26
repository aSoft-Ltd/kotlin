package tz.co.asoft.geo.usecase.location.permission.request

import tz.co.asoft.platform.core.Manifest
import tz.co.asoft.platform.core.usecase.permission.check.IPermissionCheckUseCase
import tz.co.asoft.platform.core.usecase.permission.request.IPermissionRequestUseCase
import tz.co.asoft.platform.core.usecase.permission.request.PermissionRequestUseCase

fun LocationPermissionRequestUseCase(locationPermissionCheckUC: IPermissionCheckUseCase): IPermissionRequestUseCase = PermissionRequestUseCase(
        locationPermissionCheckUC,
        listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_CHECKIN_PROPERTIES),
        "Location Permission",
        "This App/feature requires location permission to function"
)