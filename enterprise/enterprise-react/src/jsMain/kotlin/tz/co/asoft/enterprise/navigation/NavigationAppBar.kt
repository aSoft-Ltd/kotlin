package tz.co.asoft.enterprise.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.css.*
import kotlinx.html.DIV
import kotlinx.html.js.onClickFunction
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import tz.co.asoft.enterprise.appbar.AppBar
import tz.co.asoft.react.icons.MdDehaze

fun RBuilder.NavigationAppBar(
    drawerController: MutableStateFlow<DrawerState>,
    left: (StyledDOMBuilder<DIV>.() -> Unit)? = null,
    middle: (StyledDOMBuilder<DIV>.() -> Unit)? = null,
    right: (StyledDOMBuilder<DIV>.() -> Unit)? = null
) = AppBar(
    left = {
        css {
            display = Display.flex
            children { marginLeft = 0.5.rem }
            firstChild { marginLeft = 0.rem }
        }
        styledDiv {
            css {
                fontSize = 1.2.rem
                cursor = Cursor.pointer
            }
            attrs.onClickFunction = { drawerController.value = DrawerState.Opened }
            MdDehaze {}
        }

        styledDiv {
            left?.invoke(this)
        }
    },
    middle = middle,
    right = right
)