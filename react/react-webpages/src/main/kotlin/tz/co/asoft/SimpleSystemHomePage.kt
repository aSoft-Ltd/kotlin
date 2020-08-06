package tz.co.asoft

import react.RBuilder

class SimpleSystemHomePageContent(
    val title: String,
    val menus: List<Handler>,
    val inspirationalImageUrl: String,
    val intro: Intro,
    val circleSection: PictureDetailGrid,
    val moduleSection: ModuleSection,
    val demo: Demo,
    val footerSurface: Surface,
    val entityName: String
)

fun RBuilder.SimpleSystemHomePage(content: SimpleSystemHomePageContent) = Scroller {
    Grid(gap = "3em") {
        TransparentToolbar(content.title, content.menus)
        InspirationImage(content.inspirationalImageUrl)
        Introduction(content.intro)
        PictureDetailGrid(content.circleSection)
        Surface(content.footerSurface) { Demo(content.demo) }
        ModuleSection(content.moduleSection)
        Surface(content.footerSurface) { FooterRibbon(content.entityName) }
    }
}

