package tz.co.asoft.geo.usecase.location.lastknown

import tz.co.asoft.platform.core.Ctx
import tz.co.asoft.platform.core.usecase.permission.check.IPermissionCheckUseCase

expect class LastKnownLocationUseCase(ctx: Ctx, locationPermissionCheckUC: IPermissionCheckUseCase) : ILastKnownLocationUseCase