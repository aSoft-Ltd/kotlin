@file:JsModule("react-draft-wysiwyg")
@file:JsNonModule

package tz.co.asoft.enterprise.text.textedit

import react.Component
import react.RState

external class Editor : Component<TextEditor.Props, RState> {
    override fun render(): dynamic
}