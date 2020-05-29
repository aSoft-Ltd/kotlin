package tz.co.asoft.ui.react.widget.text.searchbar

import kotlinx.css.*
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
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
import tz.co.asoft.ui.module.ModuleProps
import tz.co.asoft.ui.react.icons.reacticons.faSearch
import tz.co.asoft.ui.react.tools.View
import tz.co.asoft.ui.react.widget.text.searchbar.Search.Props
import tz.co.asoft.ui.theme.dark
import tz.co.asoft.ui.theme.main
import kotlin.browser.document

class Search : RComponent<Props, RState>() {

    class Props : ModuleProps() {
        var hint = "Search..."
        var onSearch = { _: String -> }
        var css: CSSBuilder.() -> Unit = {}
    }


    private val ID = View.getId("asoft-search-box")

    private fun RBuilder.searchIcon() = styledDiv {
        css {
            width = 10.pct
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
            color = props.theme.primaryColor.main()
        }
        faSearch {}
    }

    private fun RBuilder.searchInput() = styledInput {
        css {
            width = 65.pct
            borderStyle = BorderStyle.none
            outline = Outline.none
            textAlign = TextAlign.center
        }
        attrs {
            placeholder = props.hint
            id = ID
        }
        attrs.onKeyDownFunction = {
            val e = it.unsafeCast<KeyboardEvent>()
            if (e.keyCode == 13) {
                initSearch()
            }
        }
    }

    private fun initSearch() = props.onSearch((document.getElementById(ID) as HTMLInputElement).value)
    private fun RBuilder.searchButton() = styledDiv {
        css {
            hover {
                backgroundColor = props.theme.primaryColor.dark()
                color = props.theme.text.onPrimary.dark()
            }
            color = props.theme.text.onPrimary.main()
            display = Display.flex
            width = 25.pct
            backgroundColor = props.theme.primaryColor.main()
            borderTopRightRadius = 1.em
            borderBottomRightRadius = 1.em
            textAlign = TextAlign.center
            alignItems = Align.center
            justifyContent = JustifyContent.center
            cursor = Cursor.pointer
        }
        attrs.onClickFunction = { initSearch() }
        +"Search"

    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            border = ("solid 1px ${props.theme.primaryColor.main}")
            borderRadius = 1.em
            width = 100.pct
            height = 2.em
            display = Display.flex
            maxWidth = 20.em
            minWidth = 15.em
            alignItems = Align.stretch
            +props.css
        }
        searchIcon()
        searchInput()
        searchButton()
    }
}

fun RBuilder.search(handler: RHandler<Props> = {}) = child(Search::class.js, Props(), handler)