package tz.co.asoft.enterprise.tables

import react.RClass
import tz.co.asoft.react.icons.IconProps

sealed class TAction<T>(val name: String) {
    class ContainedButton<T>(
        name: String,
        val icon: RClass<IconProps>? = null,
        val handler: (T) -> Unit
    ) : TAction<T>(name)

    class OutlinedButton<T>(
        name: String,
        val icon: RClass<IconProps>? = null,
        val handler: (T) -> Unit
    ) : TAction<T>(name)

    class TextButton<T>(
        name: String,
        val icon: RClass<IconProps>? = null,
        val handler: (T) -> Unit
    ) : TAction<T>(name)

    class LinkContainedButton<T>(
        name: String,
        val to: String,
        val icon: RClass<IconProps>? = null
    ) : TAction<T>(name)

    class LinkOutlinedButton<T>(
        name: String,
        val to: String,
        val icon: RClass<IconProps>? = null
    ) : TAction<T>(name)

    class LinkTextButton<T>(
        name: String,
        val to: String,
        val icon: RClass<IconProps>? = null
    ) : TAction<T>(name)
}