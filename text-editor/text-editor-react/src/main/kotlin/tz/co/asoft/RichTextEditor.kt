package tz.co.asoft

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import kotlinx.html.id
import org.w3c.dom.HTMLElement
import react.*
import styled.css
import styled.styledHr
import styled.styledIframe
import tz.co.asoft.RichTextEditor.Props
import tz.co.asoft.RichTextEditor.State
import kotlin.math.max

private class RichTextEditor(p: Props) : RComponent<Props, State>(p), CoroutineScope by CoroutineScope(SupervisorJob()) {
    private val frameId = UIID.getId("rich-text-editor")

    class Props(
        val content: String?,
        val type: RichTextEditorType,
        val toolbar: RBuilder.(TextEditorController) -> ReactElement?
    ) : RProps

    class State(var controller: TextEditorController) : RState

    init {
        state = State(TextEditorController(frameId))
    }

    private val initialHeight = 500

    private var theme = currentTheme.value
    override fun componentDidMount() {
        val f = state.controller.frame
        f.contentDocument?.body?.innerHTML = props.content ?: ""
        val doc = f.contentDocument ?: return
        doc.body?.addThemeSupport()
        doc.designMode = "on"
        when (val type = props.type) {
            is RichTextEditorType.Word -> {
                doc.body?.initAsA4Page()
                state.controller.apply {
                    initWordEditorController()
                    onSave(type.onSave)
                }
            }
            is RichTextEditorType.Post -> {
                doc.body?.initAsPostEditor()
                state.controller.apply {
                    initPostEditorController()
                    onSave(type.onSubmit)
                }
            }
        }
    }

    private fun HTMLElement.initAsPostEditor() {
        style.apply {
            overflowY = "hidden"
        }

        onHeightChangedWhileTyping(this@RichTextEditor) {
            style.height = "min-content"
            state.controller.frame.style.height = "${it}px"
        }
    }

    private fun HTMLElement.addThemeSupport() {
        style.apply {
            backgroundColor = theme.surfaceColor.value
            color = theme.onSurfaceColor.value
        }
    }

    private fun HTMLElement.initAsA4Page() {
        style.apply {
            padding = if (isDesktop) "3em 4em" else "1em 0.75em"
            overflowY = "hidden"
        }
        onHeightChangedWhileTyping(this@RichTextEditor) {
            style.height = "min-content"
            state.controller.frame.style.height = "${max(it, initialHeight)}px"
        }
    }

    override fun componentWillUnmount() {
        cancel()
        frameId.release()
    }

    private fun RBuilder.PostEditor() = Grid(rows = "auto 1px auto", gap = "0em") { theme ->
        css {
            border(width = 1.px, color = theme.onSurfaceColor, style = BorderStyle.solid)
            borderRadius = 5.px
            backgroundColor = theme.surfaceColor
            color = theme.onSurfaceColor
        }

        styledIframe {
            css {
                border = "none"
                width = 100.pct
                height = 3.em
                alignSelf = Align.stretch
                padding(0.2.em)
                transition(duration = 0.2.s)
            }
            attrs.id = frameId.value
        }
        styledHr { css { height = 1.px } }
        Grid {
            css { padding(0.3.em) }
            props.toolbar(this, state.controller)
        }
    }

    private fun RBuilder.WordEditor() = Grid(rows = "auto auto") { theme ->
        css {
            backgroundColor = theme.backgroundColor
            color = theme.onBackgroundColor
        }
        props.toolbar(this, state.controller)
        styledIframe {
            css {
                border = "none"
                maxWidth = 210.mm
                marginBottom = 0.5.em
                transition(duration = 0.2.s)
                onDesktop {
                    width = 100.pct
                    margin(horizontal = LinearDimension.auto)
                }
                onMobile {
                    margin(horizontal = 0.5.em)
                    width = 100.pct - 1.em
                }
                boxShadow(color = theme.onBackgroundColor.withAlpha(0.2), blurRadius = 10.px, spreadRadius = 2.px)
                height = initialHeight.px
            }
            attrs.id = frameId.value
        }
    }

    override fun RBuilder.render(): dynamic = when (props.type) {
        is RichTextEditorType.Word -> WordEditor()
        is RichTextEditorType.Post -> PostEditor()
    }
}

sealed class RichTextEditorType {
    class Word(val onSave: (String) -> Unit) : RichTextEditorType()
    class Post(val onSubmit: (String) -> Unit) : RichTextEditorType()
}

fun RBuilder.PostEditor(
    content: String? = null,
    onSubmit: (String) -> Unit
) = child(RichTextEditor::class.js, Props(content, RichTextEditorType.Post(onSubmit)) { PostEditorToolbar(it) }) {}

fun RBuilder.WordEditor(
    content: String? = null,
    onSave: ((String) -> Unit)
) = child(RichTextEditor::class.js, Props(content, RichTextEditorType.Word(onSave)) { WordEditorToolbar(it) }) {}