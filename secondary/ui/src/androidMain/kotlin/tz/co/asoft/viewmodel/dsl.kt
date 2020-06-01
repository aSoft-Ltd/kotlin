@file:JvmName("AndroidDsl")

package tz.co.asoft.viewmodel

import tz.co.asoft.platform.core.Activity
import tz.co.asoft.platform.core.Ctx

actual val Activity.appCtx: Ctx get() = applicationContext