package tz.co.asoft.enterprise.files

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.files.File
import org.w3c.files.get
import react.*
import react.dom.input
import styled.css
import styled.styledDiv
import styled.styledH4
import tz.co.asoft.UIID
import tz.co.asoft.enterprise.files.FileInput.Props
import tz.co.asoft.enterprise.files.FileInput.State
import tz.co.asoft.enterprise.progress.ProgressBar
import tz.co.asoft.onFileInputChanged

private class FileInput : RComponent<Props, State>(), CoroutineScope by CoroutineScope(SupervisorJob()) {
    class Props(
        val onFileUploaded: ((File) -> Unit)? = null,
        val isRequired: Boolean = true,
        val name: String
    ) : RProps

    class State(var currentUI: UI) : RState

    init {
        state = State(UI.NoFile)
    }

    private val FILE_INPUT_ID = UIID.getId("file-input")

    sealed class UI {
        class UploadedFile(val file: File) : UI()
        class UploadingFile(val file: File, val progress: Int) : UI()
        object NoFile : UI()
    }

    override fun componentWillUnmount() {
        FILE_INPUT_ID.release()
    }

    private fun RBuilder.showNoFile() = styledDiv {
        css {
            textAlign = TextAlign.center
            padding(1.em)
        }
        +"Click to Upload"
    }

    private fun RBuilder.showUploadedFile(file: File) {
        showUploadingFile(file.name, 100)
    }

    private fun RBuilder.showUploadingFile(fileName: String, progress: Int) {
        heading()
        ProgressBar(progress)
        styledDiv {
            css { textAlign = TextAlign.center }
            +fileName
        }
    }

    private fun RBuilder.heading() = styledH4 {
        css { textAlign = TextAlign.center }
        +"Upload"
    }

    private fun HTMLInputElement.uploadFile() = launch {
        val file = files!![0]!!
        setState { currentUI = UI.UploadedFile(file) }
        props.onFileUploaded?.invoke(file)
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

        attrs.onClickFunction = { FILE_INPUT_ID.get<HTMLInputElement>().click() }

        input(type = InputType.file) {
            attrs {
                id = FILE_INPUT_ID.value
                name = props.name
                required = props.isRequired
            }
            attrs.onFileInputChanged {
                uploadFile()
            }
        }

        when (val ui = state.currentUI) {
            is UI.UploadedFile -> showUploadedFile(ui.file)
            is UI.UploadingFile -> showUploadingFile(ui.file.name, ui.progress)
            UI.NoFile -> showNoFile()
        }
    }
}

fun RBuilder.FileInput(
    name: String,
    isRequired: Boolean = true,
    onFileUploaded: ((File) -> Unit)? = null
) = child(FileInput::class.js, Props(onFileUploaded, isRequired, name)) {}