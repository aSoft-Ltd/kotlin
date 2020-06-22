package tz.co.asoft.geo.usecase.location.lastknown

import tz.co.asoft.geo.Cord
import tz.co.asoft.platform.core.Ctx
import tz.co.asoft.platform.core.usecase.permission.check.IPermissionCheckUseCase

actual class LastKnownLocationUseCase actual constructor(ctx: Ctx, locationPermissionCheckUC: IPermissionCheckUseCase) : ILastKnownLocationUseCase {
    override fun invoke(): Cord? {
        TODO("${LastKnownLocationUseCase::class.simpleName} not implemented in js module")
    }
}