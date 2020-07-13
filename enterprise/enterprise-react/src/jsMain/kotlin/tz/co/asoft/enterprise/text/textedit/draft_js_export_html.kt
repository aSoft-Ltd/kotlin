package tz.co.asoft.enterprise.text.textedit

import kotlinext.js.require

private val draftJsExportHtml = require("draft-js-export-html")

val stateToHTML: (EditorContent) -> String = draftJsExportHtml.stateToHTML
