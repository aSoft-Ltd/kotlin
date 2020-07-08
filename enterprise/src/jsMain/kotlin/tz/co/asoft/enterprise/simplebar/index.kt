@file:JsModule("simplebar-react")

package tz.co.asoft.enterprise.simplebar

import react.RClass
import styled.StyledProps

external interface SimpleBarProps : StyledProps

@JsName("default")
external val SimpleBarReact: RClass<SimpleBarProps>