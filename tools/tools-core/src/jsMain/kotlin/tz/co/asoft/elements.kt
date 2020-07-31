package tz.co.asoft

import kotlinx.css.TagSelector
import org.w3c.dom.Element
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.asList

operator fun HTMLFormElement.get(name: String): String? {
    val node = input(name)
    return if (node == undefined) {
        null
    } else {
        if (node.value == undefined) {
            null
        } else {
            node.value
        }
    }
}

fun HTMLFormElement.error(name: String, error: String) {
    val node = input(name) ?: return
    val prevDisplay = node.style.display
    console.log(prevDisplay)
    if (prevDisplay == "none") {
        node.style.display = "inline"
    }
    node.setCustomValidity(error)
    node.oninput = {
        node.style.display = prevDisplay
        node.setCustomValidity("")
    }
}

val submit get() = TagSelector("""[type="submit"]""")

fun <E : Element> Element.node(selector: TagSelector) = querySelector(selector.tagName).unsafeCast<E?>()
fun <E : Element> Element.nodes(selector: TagSelector) = querySelectorAll(selector.tagName).unsafeCast<E?>()
fun <E : Element> Element.nodeNamed(name: String) = querySelector("""[name="$name"]""").unsafeCast<E?>()
fun Element.input(name: String) = nodeNamed<HTMLInputElement>(name)

fun HTMLInputElement.type(text: String) {
    value = text
}

class By(val selector: String) {
    companion object {
        fun id(id: String) = By("#$id")
        fun name(name: String) = attr("name", name)
        fun className(className: String) = By(".$className")
        fun attr(name: String, value: String) = By("[$name='$value']")
        fun selector(selector: String) = By(selector)
        fun tag(name: String) = By(name)
        fun value(value: String) = attr("value", value)
    }
}

fun <E : Element> Element.find(by: By) = querySelector(by.selector).unsafeCast<E>()
fun <E : Element> Element.findAll(by: By) = querySelectorAll(by.selector).asList().unsafeCast<List<E>>()