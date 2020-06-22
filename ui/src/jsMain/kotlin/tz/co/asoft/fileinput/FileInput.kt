package tz.co.asoft.fileinput

import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.files.get
import react.RBuilder
import react.RHandler
import react.dom.input
import react.setState
import styled.css
import styled.styledDiv
import styled.styledH4
import tz.co.asoft.component.Component
import tz.co.asoft.components.CState
import tz.co.asoft.components.ModuleProps
import tz.co.asoft.fileinput.FileInput.Props
import tz.co.asoft.fileinput.FileInput.State
import tz.co.asoft.io.File
import tz.co.asoft.ui.react.tools.By
import tz.co.asoft.ui.react.tools.View
import tz.co.asoft.ui.react.tools.css
import tz.co.asoft.ui.react.tools.find
import tz.co.asoft.ui.react.widget.progress.progressBar
import kotlin.browser.document

class FileInput : Component<Props, State>() {
    class Props : ModuleProps() {
        var documentTitle = ""
        var onFileUploaded = { _: File -> }
        var isRequired = true
        var name = documentTitle
    }

    class State : CState() {
        var currentUI: UI = UI.NoFile
    }

    init {
        state = State()
    }

    private val FILE_INPUT_ID = View.getId("file-input")

    sealed class UI {
        class UploadedFile(val file: File) : UI()
        class UploadingFile(val file: File, val progress: Int) : UI()
        object NoFile : UI()
    }

    private fun RBuilder.showNoFile() = styledDiv {
        css {
            textAlign = TextAlign.center
            padding(1.em)
        }
        +"Click to Upload ${props.documentTitle}"
    }

    private fun RBuilder.showUploadedFile(file: File) {
        showUploadingFile(file.name, 100)
    }

    private fun RBuilder.showUploadingFile(fileName: String, progress: Int) {
        heading()
        progressBar(progress) {
            css { width = 100.pct }
            attrs { theme = props.theme }
        }
        styledDiv {
            css { textAlign = TextAlign.center }
            +fileName
        }
    }

    private fun RBuilder.heading() = styledH4 {
        css { textAlign = TextAlign.center }
        +props.documentTitle
    }

    private fun HTMLInputElement.uploadFile() = launch {
        val file = files!![0]!!
//        file.readBytes {
//            setState { ui = UI.UploadingFile(file, it) }
//        }
        setState { currentUI = UI.UploadedFile(file) }
        props.onFileUploaded(file)
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            width = 100.pct
            display = Display.grid
            gridTemplateColumns = GridTemplateColumns("1fr")
            gap = Gap("1em")
            cursor = Cursor.pointer
            boxShadow(Color.gray, blurRadius = 4.px, spreadRadius = 2.px)
            child("input") {
                display = Display.none
            }
        }

        attrs.onClickFunction = {
            document.documentElement?.find<HTMLInputElement>(By.id(FILE_INPUT_ID))?.click()
        }

        input(type = InputType.file) {
            attrs {
                id = FILE_INPUT_ID
                name = props.name
                required = props.isRequired
            }
            attrs.onChangeFunction = {
                it.target.unsafeCast<HTMLInputElement>().uploadFile()
            }
        }

        when (val ui = state.currentUI) {
            is UI.UploadedFile -> showUploadedFile(ui.file)
            is UI.UploadingFile -> showUploadingFile(ui.file.name, ui.progress)
            UI.NoFile -> showNoFile()
        }
    }
}

fun RBuilder.fileInput(handler: RHandler<Props> = {}) = child(FileInput::class.js, Props(), handler)