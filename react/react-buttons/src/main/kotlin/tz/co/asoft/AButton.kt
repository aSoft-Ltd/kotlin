package tz.co.asoft

import react.RClass

sealed class AButton<T>(val name: String, val icon: RClass<IconProps>?, val handler: (T) -> Unit) {
    class Contained<T>(name: String, icon: RClass<IconProps>? = null, handler: (T) -> Unit) : AButton<T>(name, icon, handler)
    class Outlined<T>(name: String, icon: RClass<IconProps>? = null, handler: (T) -> Unit) : AButton<T>(name, icon, handler)
    class Text<T>(name: String, icon: RClass<IconProps>? = null, handler: (T) -> Unit) : AButton<T>(name, icon, handler)
}