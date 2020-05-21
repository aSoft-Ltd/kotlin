import react.*
import react.dom.render
import styled.styledDiv
import tz.co.asoft.ui.react.widget.button.primaryButton
import kotlin.browser.document

fun main() {
    render(document.getElementById("root")) {
        Counter()
    }
}

fun RBuilder.Counter() = child(functionalComponent<RProps> {
    val (count, setCount) = useState(100)
    primaryButton("+") {
        attrs.onClick = { setCount(count + 1) }
    }
    styledDiv { +"Count: $count" }
    primaryButton("-") {
        attrs.onClick = { setCount(count - 1) }
    }
})