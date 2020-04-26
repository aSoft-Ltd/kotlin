package tz.co.asoft.components

import tz.co.asoft.ui.theme.Theme

@Deprecated("Abandone the use of CProps")
actual open class CProps actual constructor() {
    @Transient
    var theme = Theme()
}