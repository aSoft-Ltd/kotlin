@file:JsModule("simplebar-react")

package tz.co.asoft.enterprise.simplebar

import react.RClass
import tz.co.asoft.tools.StyledProps

external interface SimpleBarProps : StyledProps

@JsName("default")
internal external val SimpleBarReact: RClass<SimpleBarProps>