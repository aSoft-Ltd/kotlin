package tz.co.asoft.ui.react.widget.text.searchbar

import tz.co.asoft.ui.theme.main
import tz.co.asoft.ui.react.tools.ThemedProps
import tz.co.asoft.ui.react.widget.text.searchbar.SearchBox.Props
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.borderBottomColor
import kotlinx.css.color
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onKeyDownFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.KeyboardEvent
import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.css
import styled.styledDiv
import styled.styledInput
import kotlin.browser.document

class SearchBox(p: Props) : RComponent<Props, RState>(p) {

    class Props : ThemedProps() {
        var hint = ""
        var onSearch = { _: String -> }
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            +SearchBarStyles.root
            before {
                backgroundColor = props.theme.primaryColor.main()
            }
            +props.css
        }

        styledInput(type = InputType.text) {
            attrs {
                id = "asoft-view-search-bar"
                placeholder = "Search . . ."
                onKeyDownFunction = {
                    val e = it.unsafeCast<KeyboardEvent>()
                    if (e.keyCode == 13) {
                        props.onSearch((document.getElementById(id) as HTMLInputElement).value)
                    }
                }
            }

            css {
                +SearchBarStyles.input
                props.theme.let {
                    color = Color(it.text.onBackground.light)

                    focus {
                        borderBottomColor = it.primaryColor.main()
                    }
                }
            }
        }
    }
}

@Deprecated("Use search instead")
fun RBuilder.searchBox(handler: RHandler<Props> = {}) = child(SearchBox::class.js, Props()) {
    handler()
}