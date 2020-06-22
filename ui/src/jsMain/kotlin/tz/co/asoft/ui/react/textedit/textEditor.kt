package tz.co.asoft.ui.react.textedit

import kotlinx.css.*
import react.RBuilder
import react.RHandler
import styled.css
import styled.styledDiv
import tz.co.asoft.components.CState
import tz.co.asoft.components.Component
import tz.co.asoft.components.ModuleProps
import tz.co.asoft.ui.react.textedit.TextEditor.Props
import tz.co.asoft.ui.react.textedit.TextEditor.State

private var isTextEditorCssLoaded = false

external interface EditorContent {
    fun getPlainText(): String
}

external interface TextEditorState {
    fun getCurrentContent(): EditorContent
}

fun TextEditorState.toJson() = convertToRaw(getCurrentContent())
fun TextEditorState.toHtml() = stateToHTML(getCurrentContent())

class TextEditor : Component<Props, State>() {
    class Props : ModuleProps() {
        var css: CSSBuilder.() -> Unit = {}
        var editorState: TextEditorState? = null
        var toolbarClassName: String? = undefined
        var wrapperClassName: String? = undefined
        var editorClassName: String? = undefined
        var onEditorStateChange: (_: TextEditorState) -> Unit = {}
    }

    class State : CState() {
        var editorState: TextEditorState = EditorState.createEmpty()
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        child(Editor::class.js, Props()) {
            if (!isTextEditorCssLoaded) {
                kotlinext.js.require("react-draft-wysiwyg/dist/react-draft-wysiwyg.css")
                isTextEditorCssLoaded = true
            }
            attrs {
                state.editorState?.let { editorState = it }
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
            border = "solid 2px ${props.theme.primaryColor.main}"

            child("div") {
                padding(1.em)
                minHeight = 50.vh
            }
            +props.css
        }
    }
}

fun RBuilder.textEditor(handler: RHandler<Props>) = child(TextEditor::class.js, Props(), handler)