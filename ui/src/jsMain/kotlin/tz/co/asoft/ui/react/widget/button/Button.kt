package tz.co.asoft.ui.react.widget.button

import tz.co.asoft.ui.theme.dark
import tz.co.asoft.ui.theme.main
import tz.co.asoft.ui.tools.newJsObject
import tz.co.asoft.ui.react.tools.ThemedProps
import tz.co.asoft.ui.react.widget.button.Button.Props
import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.html.ButtonType
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseDownFunction
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledButton

class Button(p: Props) : RComponent<Props, RState>(p) {

    class Props : ThemedProps() {
        var type = Type.Primary
        var text = ""
        var isSubmit = false
        var onMouseDown = {}
    }

    enum class Type {
        Primary, Normal, Raised, Secondary
    }

    private val bgCol
        get() = when (props.type) {
            Type.Normal -> props.theme.backgroundColor
            Type.Secondary -> props.theme.secondaryColor
            else -> props.theme.primaryColor
        }

    private val borderCol
        get() = if (props.type == Type.Normal) {
            props.theme.primaryColor
        } else bgCol

    private val textCol
        get() = when (props.type) {
            Type.Normal -> props.theme.text.onBackground.main()
            Type.Secondary -> props.theme.text.onSecondary.main()
            else -> props.theme.text.onPrimary.main()
        }

    override fun RBuilder.render(): dynamic = styledButton {
        css {
            backgroundColor = bgCol.main()
            border(2.px, BorderStyle.solid, borderCol.main())
            borderRadius = 4.px
            color = textCol
            padding(0.4.em)
            cursor = Cursor.pointer
            put("font-size", "inherit")
            +props.css

            hover {
                backgroundColor = bgCol.dark()
                border(2.px, BorderStyle.solid, borderCol.dark())
            }

            focus { outline = Outline.none }
        }
        
        attrs.type = if (props.isSubmit) ButtonType.submit else ButtonType.button

        attrs.onClickFunction = {
            if (!props.isSubmit) {
                it.preventDefault()
            }
            props.onClick()
        }

        attrs.onMouseDownFunction = { props.onMouseDown() }

        props.data.forEach { (k, v) ->
            attrs["data-$k"] = v
        }

        +props.text
    }
}

fun RBuilder.button(
        text: String = "P Button",
        type: Button.Type = Button.Type.Primary,
        handler: RHandler<Props> = {}
) = child(Button::class.js, Props()) {
    attrs.text = text
    attrs.type = type
    attrs.data["value"] = text
    handler()
}

fun RBuilder.primaryButton(text: String = "P Button", handler: RHandler<Props> = {}) =
        button(text, Button.Type.Primary, handler)

fun RBuilder.normalButton(text: String = "N Button", handler: RHandler<Props> = {}) =
        button(text, Button.Type.Normal, handler)

fun RBuilder.secondaryButton(text: String = "S Button", handler: RHandler<Props> = {}) =
        button(text, Button.Type.Secondary, handler)