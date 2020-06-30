@file:JsModule("draft-js")
@file:JsNonModule

package tz.co.asoft.enterprise.text.textedit

external object EditorState {
    fun createEmpty(): TextEditorState
    fun createWithContent(content: EditorContent): TextEditorState
}

external fun convertToRaw(content: EditorContent): String


