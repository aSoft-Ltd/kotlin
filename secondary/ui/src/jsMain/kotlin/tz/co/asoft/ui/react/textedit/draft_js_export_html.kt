package tz.co.asoft.ui.react.textedit

import kotlinext.js.require

private val draftJsExportHtml = require("draft-js-export-html")

val stateToHTML: (EditorContent) -> String = draftJsExportHtml.stateToHTML
