package tz.co.asoft

import kotlinx.css.*
import react.RBuilder
import styled.css

private fun RBuilder.WordingToolbarSection(controller: ITextEditorController) = Grid(rows = "1fr 1fr", gap = "0.5em") { theme ->
    css { minHeight = 5.em }
    Grid(cols = "1fr auto", gap = "0.5em") {
        FontSelector { controller.setFontName(it) }
        val sizes = (1..7).associateBy { "${it * 4} px" }
        DropDown(
            value = "12 px",
            options = listOf("Font Size") + sizes.keys.toList(),
            name = "font-sizes",
            onChange = { controller.setFontSize(sizes[it].toString()) }
        )
    }

    Grid(cols = "1fr ".repeat(6), gap = "0.5em") {
        css {
            children {
                justifySelf = JustifyContent.center
                alignSelf = Align.center
            }
        }
        SIcon(FaBold) { controller.bold() }
        SIcon(FaItalic) { controller.italic() }
        SIcon(FaUnderline) { controller.underline() }
        SIcon(FaStrikethrough) { controller.strikeThrough() }
        ForeColor { controller.setForeColor(it) }
        HighliteColor { controller.setHighliteColor(it) }
    }
}

private fun RBuilder.AlignmentSection(controller: ITextEditorController) = Grid(rows = "1fr 1fr", gap = "0.5em") {
    css { minHeight = 5.em }
    Grid("1fr 1fr 1fr 1fr", gap = "0.5em") {
        css { children { alignSelf = Align.center } }
        SIcon(FaSuperscript, "Super") { controller.unorderedList() }
        SIcon(FaSubscript, "Sub") { controller.orderedList() }
        SIcon(FaIndent, "Indent") { controller.indent() }
        SIcon(FaOutdent, "Outdent") { controller.outdent() }
    }

    Grid("1fr 1fr 1fr 1fr", gap = "0.5em") {
        css { children { alignSelf = Align.center } }
        SIcon(FaAlignLeft, "Left") { controller.alignLeft() }
        SIcon(FaAlignCenter, "Center") { controller.alignCenter() }
        SIcon(FaAlignRight, "Right") { controller.alignRight() }
        SIcon(FaAlignJustify, "Justify") { controller.alignJustify() }
    }
}

private fun RBuilder.HomeDesktopToolbarSection(
    controller: ITextEditorController
) = Grid(cols = "auto ".repeat(6)) {
    css { minHeight = 4.em }
    BIcon(FaSave, "Save") { controller.save() }
    BIcon(FaPaste, "Paste") { controller.paste() }
    Grid(cols = "auto auto", rows = "auto auto", gap = "0.5em") {
        css { children { alignSelf = Align.center } }
        SIcon(FaCut, "Cut") { controller.cut() }
        SIcon(FaUndo, "Undo") { controller.undo() }
        SIcon(FaCopy, "Copy") { controller.copy() }
        SIcon(FaRedo, "Redo") { controller.redo() }
    }
    WordingToolbarSection(controller)
    AlignmentSection(controller)
}

private fun RBuilder.HomeMobileToolbarSection(controller: ITextEditorController) = Grid(cols = "auto auto 1fr", gap = "0.5em") {
    css { minHeight = 5.em }
    BIcon(FaSave, "Save") { controller.save() }
    Grid(rows = "auto auto", gap = "0.5em") {
        css { children { alignSelf = Align.center } }
        SIcon(FaUndo, "Undo") { controller.undo() }
        SIcon(FaRedo, "Redo") { controller.redo() }
    }
    WordingToolbarSection(controller)
}

private fun RBuilder.InsertToolbarSection(controller: ITextEditorController) = Grid(cols = "auto auto auto auto 1fr") {
    css { minHeight = 5.em }
    BIcon(FaImage, "Image") { controller.insertImage(prompt("Enter Image URL", "http://")) }
    BIcon(FaTable, "Table") {}
    Grid(gap = "0.5.em", rows = "auto auto") {
        SIcon(FaLink, "Link") { controller.insertLink(prompt("Enter Link URL", "http://")) }
        SIcon(FaUnlink, "Unlink") { controller.unlink() }
    }
    Grid(gap = "0.5.em", rows = "auto auto") {
        SIcon(FaListOl, "Numbers") { controller.orderedList() }
        SIcon(FaListUl, "Bullets") { controller.unorderedList() }
    }
}

private fun RBuilder.StylesSectionToolbar(controller: ITextEditorController) = Grid {
    css {
        minHeight = 5.em
        onDesktop { gridTemplateColumns = GridTemplateColumns("auto ".repeat(7) + "1fr") }
        onMobile { gridTemplateColumns = GridTemplateColumns("auto ".repeat(4)) }
    }
    (1..6).associateWith {
        when (it) {
            1 -> 2.0.rem
            2 -> 1.5.rem
            3 -> 1.17.rem
            4 -> 1.rem
            5 -> 0.83.rem
            else -> 0.67.rem
        }
    }.forEach { (index, size) ->
        if (isMobile && index > 4) return@forEach
        BCharIcon('H', "Heading $index", size = size) { controller.setHeading("H$index") }
    }
}

fun RBuilder.WordEditorToolbar(controller: ITextEditorController) = Grid {
    css {
        position = Position.sticky
        top = 0.px
        left = 0.px
    }
    if (isDesktop) Tabs(
        Tab("Home", FaHome) { HomeDesktopToolbarSection(controller) },
        Tab("Insert", FaSignInAlt) { InsertToolbarSection(controller) },
        Tab("Styles", FaHeading) { StylesSectionToolbar(controller) }
    ) else Tabs(
        Tab("Home", FaHome) { HomeMobileToolbarSection(controller) },
        Tab("Format", FaSlidersH) { AlignmentSection(controller) },
        Tab("Insert", FaSignInAlt) { InsertToolbarSection(controller) },
        Tab("Styles", FaHeading) { StylesSectionToolbar(controller) }
    )
}