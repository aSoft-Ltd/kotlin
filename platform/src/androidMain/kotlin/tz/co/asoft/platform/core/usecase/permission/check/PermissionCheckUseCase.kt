package tz.co.asoft.platform.core.usecase.permission.check

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import tz.co.asoft.platform.core.Ctx

actual open class PermissionCheckUseCase actual constructor(private val ctx: Ctx, private val perm: String) : IPermissionCheckUseCase {
    override fun isPermitted() = ContextCompat.checkSelfPermission(ctx, perm) == PackageManager.PERMISSION_GRANTED
}