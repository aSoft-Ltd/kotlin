package tz.co.asoft.platform.core.usecase.permission.request

import android.app.AlertDialog
import androidx.core.app.ActivityCompat
import tz.co.asoft.platform.core.Activity
import tz.co.asoft.platform.core.usecase.permission.check.IPermissionCheckUseCase

actual class PermissionRequestUseCase actual constructor(
        private val permCheckUC: IPermissionCheckUseCase,
        private val perms: List<String>,
        private val title: String,
        private val msg: String
) : IPermissionRequestUseCase {

    override fun invoke(a: Activity, requestCode: Int) {
        if (permCheckUC.notPermitted) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(a, perms.first())) {
                AlertDialog.Builder(a).apply {
                    setTitle(title)
                    setMessage(msg)
                    setPositiveButton("Ok") { _, _ ->
                        ActivityCompat.requestPermissions(a, perms.toTypedArray(), requestCode)
                    }
                    create()
                    show()
                }
            } else {
                ActivityCompat.requestPermissions(a, perms.toTypedArray(), requestCode)
            }
        }
    }
}