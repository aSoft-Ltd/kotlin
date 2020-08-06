package tz.co.asoft

import kotlinx.html.DIV
import react.RClass
import styled.StyledDOMBuilder

class Tab(
    val name: String,
    val icon: RClass<*>? = null,
    val isCloseable: Boolean = false,
    val content: StyledDOMBuilder<DIV>.(Theme) -> Any
)