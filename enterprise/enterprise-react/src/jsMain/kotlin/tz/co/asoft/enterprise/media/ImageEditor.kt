package tz.co.asoft.enterprise.media

import kotlinx.css.Align
import kotlinx.css.LinearDimension
import kotlinx.css.alignSelf
import kotlinx.css.pct
import org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio
import org.w3c.dom.url.URL
import org.w3c.files.File
import react.*
import styled.css
import tz.co.asoft.enterprise.buttons.ContainedButton
import tz.co.asoft.enterprise.buttons.OutlinedButton
import tz.co.asoft.enterprise.layout.Grid
import tz.co.asoft.enterprise.media.ImageEditor.Props
import tz.co.asoft.enterprise.media.ImageEditor.State
import tz.co.asoft.enterprise.progress.ProgressBar

private class ImageEditor : RComponent<Props, State>() {
    class Props(
        val file: File,
        val textFontSize: LinearDimension,
        val onCancel: (() -> Unit),
        val aspectRatio: Float,
        val onSubmit: (File) -> Unit
    ) : RProps

    class State(var ui: UI) : RState

    sealed class UI {
        object Loading : UI()
        class Editing(val url: String) : UI()
    }

    init {
        state = State(UI.Loading)
    }

    lateinit var imageUrl: String
    override fun componentDidMount() {
        imageUrl = URL.createObjectURL(props.file)
        setState { ui = UI.Editing(imageUrl) }
    }

    override fun componentWillUnmount() {
        URL.revokeObjectURL(imageUrl)
    }

    private fun RBuilder.PreviewAndActions(url: String) = Grid(cols = "1fr 1fr 1fr") {
        css {
            children { alignSelf = Align.center }
        }
        ProfilePic(
            radius = 50.pct,
            name = "A",
            textFontSize = props.textFontSize,
            aspectRatio = props.aspectRatio,
            src = url
        )
        OutlinedButton("Cancel", onClick = props.onCancel)
        ContainedButton("Submit") { props.onSubmit(props.file) }
    }

    override fun RBuilder.render(): dynamic = when (val ui = state.ui) {
        UI.Loading -> ProgressBar()
        is UI.Editing -> Grid(rows = "1fr auto") {
            ProfilePic("A", ui.url, props.aspectRatio, textFontSize = props.textFontSize)
            PreviewAndActions(ui.url)
        }
    }
}

fun RBuilder.ImageEditor(
    file: File,
    textFontSize: LinearDimension,
    onCancel: () -> Unit,
    onSubmit: (File) -> Unit,
    aspectRatio: Float
) = child(ImageEditor::class.js, Props(file, textFontSize, onCancel, aspectRatio, onSubmit)) {}