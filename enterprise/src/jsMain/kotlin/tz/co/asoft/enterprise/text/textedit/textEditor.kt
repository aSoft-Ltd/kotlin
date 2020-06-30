package tz.co.asoft.enterprise.text.textedit

import kotlinx.css.*
import react.*
import styled.css
import styled.styledDiv
import tz.co.asoft.enterprise.text.textedit.TextEditor.Props
import tz.co.asoft.enterprise.text.textedit.TextEditor.State
import tz.co.asoft.theme.ThemeConsumer

private var isTextEditorCssLoaded = false

external interface EditorContent {
    fun getPlainText(): String
}

external interface TextEditorState {
    fun getCurrentContent(): EditorContent
}

fun TextEditorState.toJson() = convertToRaw(getCurrentContent())
fun TextEditorState.toHtml() = stateToHTML(getCurrentContent())

class TextEditor : RComponent<Props, State>() {
    class Props : RProps {
        var css: CSSBuilder.() -> Unit = {}
        var editorState: TextEditorState? = null
        var toolbarClassName: String? = undefined
        var wrapperClassName: String? = undefined
        var editorClassName: String? = undefined
        var onEditorStateChange: (_: TextEditorState) -> Unit = {}
    }

    class State : RState {
        var editorState: TextEditorState = EditorState.createEmpty()
    }

    override fun RBuilder.render(): dynamic = ThemeConsumer { theme ->
        styledDiv {
            child(Editor::class.js, Props()) {
                if (!isTextEditorCssLoaded) {
                    kotlinext.js.require("react-draft-wysiwyg/dist/react-draft-wysiwyg.css")
                    isTextEditorCssLoaded = true
                }
                attrs {
                    state.editorState.let { editorState = it }
                    toolbarClassName = props.toolbarClassName
                    wrapperClassName = props.wrapperClassName
                    editorClassName = props.editorClassName
                    onEditorStateChange = {
                        setState { editorState = it }
                        props.onEditorStateChange(it)
                    }
                }
            }
            css {
                position = Position.relative
                border = "solid 2px ${theme.color.primary}"

                child("div") {
                    padding(1.em)
                    minHeight = 50.vh
                }
                +props.css
            }
        }
    }
}

fun RBuilder.TextEditor(handler: RHandler<Props>) = child(TextEditor::class.js, Props(), handler)