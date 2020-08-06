package tz.co.asoft

import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onLoadFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.files.File
import org.w3c.files.get
import react.*
import styled.css
import styled.styledImg
import styled.styledInput
import tz.co.asoft.ProfilePic.Props
import tz.co.asoft.ProfilePic.State
import kotlin.lazy
import kotlin.Float

private class ProfilePic : RComponent<Props, State>() {
    class Props(
        val name: String,
        val src: String?,
        val aspectRatio: Float,
        val radius: LinearDimension,
        val textFontSize: LinearDimension,
        val onEdit: ((File) -> Any)?
    ) : RProps

    class State(var loading: Boolean) : RState

    init {
        state = State(true)
    }

    private val IMAGE_INPUT_ID by lazy { UIID.getId("profile-pic") }

    private val imageInput get() = IMAGE_INPUT_ID.get<HTMLInputElement>()

    override fun componentWillUnmount() {
        IMAGE_INPUT_ID.release()
    }

    private fun RBuilder.InvisibleImageInput() = styledInput(type = InputType.file) {
        css {
            position = Position.absolute
            left = 0.px
            top = 0.px
            visibility = Visibility.hidden
        }
        attrs {
            id = IMAGE_INPUT_ID.value
            accept = "image/*"
            onChangeFunction = { ev ->
                ev.target?.to<HTMLInputElement>()?.files?.get(0)?.let {
                    props.onEdit?.invoke(it)
                }
            }
        }
    }

    private fun RBuilder.LoadingWidget() = Grid { theme ->
        css {
            width = 100.pct
            height = 100.pct
            position = Position.absolute
            border(width = 2.px, color = theme.primaryColor, style = BorderStyle.solid, borderRadius = props.radius)
            children {
                justifySelf = JustifyContent.center
                alignSelf = Align.center
            }
        }
        ProgressBar()
    }

    override fun RBuilder.render(): dynamic = AspectRationDiv(props.aspectRatio) {
        InvisibleImageInput()
        props.onEdit?.let {
            attrs.onClickFunction = { imageInput.click() }
        }
        if (props.src == null || props.src.isBlank()) {
            TextProfilePic(props.name, textFontSize = props.textFontSize, radius = props.radius)
        } else {
            styledImg(src = props.src, alt = props.name) {
                css {
                    width = 100.pct
                    height = 100.pct
                    borderRadius = props.radius
                    cursor = Cursor.pointer
                }
                attrs.onLoadFunction = { setState { loading = false } }
            }
            if (state.loading) {
                LoadingWidget()
            }
        }
    }
}

fun RBuilder.ProfilePic(
    name: String,
    src: String? = null,
    aspectRatio: Float = 1f / 1f,
    radius: LinearDimension = 10.px,
    textFontSize: LinearDimension = 1.rem,
    onEdit: ((File) -> Any)? = null
) = child(ProfilePic::class.js, Props(name, src, aspectRatio, radius, textFontSize, onEdit)) {}