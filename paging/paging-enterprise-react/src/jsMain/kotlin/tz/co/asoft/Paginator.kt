package tz.co.asoft

import react.RBuilder
import styled.styledDiv
import tz.co.asoft.enterprise.buttons.ContainedButton
import tz.co.asoft.enterprise.layout.Grid
import tz.co.asoft.react.icons.MdArrowBack
import tz.co.asoft.react.icons.MdArrowForward

fun RBuilder.Paginator(
    onPrev: (() -> Unit)? = null,
    onNext: (() -> Unit)? = null
) = Grid(cols = "auto auto auto") {
    if (onPrev != null) {
        ContainedButton("Prev", MdArrowBack, onPrev)
    } else {
        styledDiv { }
    }

    styledDiv { }

    if (onNext != null) {
        ContainedButton("Next", MdArrowForward, onNext)
    } else {
        styledDiv { }
    }
}