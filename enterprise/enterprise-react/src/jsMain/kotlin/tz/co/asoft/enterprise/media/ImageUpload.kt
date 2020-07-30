package tz.co.asoft.enterprise.media

import kotlinx.css.*
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.files.File
import org.w3c.files.FileReader
import org.w3c.files.get
import react.*
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledInput
import tz.co.asoft.UIID
import tz.co.asoft.enterprise.media.ImageUpload.Props
import tz.co.asoft.enterprise.media.ImageUpload.State

private class ImageUpload(p: Props) : RComponent<Props, State>(p) {
    class Props(
        val data: Map<String, Any>?,
        val onUploaded: ((File, String) -> Unit)?
    ) : RProps

    class State(var imageSrc: String) : RState

    init {
        state = State("")
    }

    private val INPUT_ID = UIID.getId("input")

    private val inputId = INPUT_ID.value

    private val input get() = INPUT_ID.get<HTMLInputElement>()

    private fun readImage() {
        input.files?.let {
            val file = it[0]!!
            val fileReader = FileReader()
            fileReader.onload = {
                setState {
                    imageSrc = (it.target as FileReader).result as String
                    props.onUploaded?.invoke(file, imageSrc)
                }
            }
            fileReader.readAsDataURL(file)
        }
    }

    override fun RBuilder.render(): dynamic = styledDiv {
        css {
            position = Position.relative
            backgroundColor = Color.gray
            display = Display.flex
            justifyContent = JustifyContent.center
            alignItems = Align.center
            cursor = Cursor.pointer
            margin(2.pct)
            maxWidth = 20.em
            maxHeight = 20.em
            textAlign = TextAlign.center
        }

        attrs {
            onClickFunction = { input.click() }
        }

        if (state.imageSrc.isEmpty().not()) {
            styledImg(src = state.imageSrc) {
                css {
                    width = 100.pct
                    height = 100.pct
                }
            }
        } else {
            styledDiv {
                +"Upload Picture"
            }
        }

        styledInput(type = InputType.file) {
            attrs {
                id = inputId
                accept = "image/*"
                onChangeFunction = {
                    readImage()
                }
            }
            css {
                position = Position.absolute
                visibility = Visibility.hidden
            }

        }
    }
}

fun RBuilder.ImageUpload(
    data: Map<String, Any>? = null,
    onUploaded: ((File, String) -> Unit)? = null
) = child(ImageUpload::class.js, Props(data, onUploaded)) {}

