package tz.co.asoft

import org.w3c.dom.HTMLIFrameElement

class TextEditorController(private val frameId: UIID) : ITextEditorController {
    val frame get() = frameId.get<HTMLIFrameElement>()

    private var saveHandler: ((String) -> Unit)? = null

    private fun send(cmd: String, arg: String? = null) {
        val doc = frame.contentDocument
        if (arg != null) {
            doc?.execCommand(cmd, false, arg)
        } else {
            doc?.execCommand(cmd, false)
        }
    }

    fun initWordEditorController() {
        enableInlineTableEditing()
        styleWithCss()
        enableObjectResizing()
    }

    fun initPostEditorController() {
        styleWithCss()
    }

    override fun bold() = send("bold")

    override fun italic() = send("italic")

    override fun underline() = send("underline")

    override fun strikeThrough() = send("strikeThrough")

    override fun alignLeft() = send("justifyLeft")

    override fun alignCenter() = send("justifyCenter")

    override fun alignRight() = send("justifyRight")

    override fun alignJustify() = send("justifyFull")

    override fun cut() = send("cut")

    override fun copy() = send("copy")

    override fun paste() = send("paste")

    override fun indent() = send("indent")

    override fun outdent() = send("outdent")

    override fun subscript() = send("subscript")

    override fun superscript() = send("superscript")

    override fun undo() = send("undo")

    override fun redo() = send("redo")

    override fun unorderedList() = send("insertUnorderedList")

    override fun orderedList() = send("insertOrderedList")

    override fun paragraph() = send("paragraph")

    override fun insertHorizontalRule() = send("insertHorizontalRule")

    override fun insertLink(url: String) = send("createLink", url)

    override fun unlink() = send("unlink")

    override fun setHeading(h: String) = send("formatBlock", h)

    override fun setNormal() = send("removeFormat")

    override fun setFontName(f: String) = send("fontName", f)

    override fun setForeColor(c: String) = send("foreColor", c)

    override fun setHighliteColor(c: String) = send("hiliteColor", c)

    override fun insertImage(url: String) = send("insertImage", url)

    override fun save() {
        saveHandler?.invoke(frame.contentDocument?.body?.innerHTML ?: "")
    }

    override fun onSave(handler: (String) -> Unit) {
        saveHandler = handler
    }

    override fun setFontSize(size: String) = send("fontSize", size)

    override fun insertHTML(html: String) = send("insertHTML", html)

    override fun increaseFontSize() = send("increaseFontSize")

    override fun decreaseFontSize() = send("decreaseFontSize")

    override fun removeFormat() = send("removeFormat")

    override fun formatBlock(tag: String) = send("formatBlock", tag)

    override fun enableInlineTableEditing() = send("enableInlineTableEditing")

    override fun enableObjectResizing() = send("enableObjectResizing")

    override fun styleWithCss() = send("styleWithCSS", "true")

    override fun insertText(txt: String) = send("insertText", txt)

    override fun insertParagraph(paragraph: String) = send("insertParagraph", paragraph)
}