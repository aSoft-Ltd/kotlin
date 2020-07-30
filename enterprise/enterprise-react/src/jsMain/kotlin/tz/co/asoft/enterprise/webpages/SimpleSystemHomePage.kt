package tz.co.asoft.enterprise.webpages

import react.RBuilder
import tz.co.asoft.enterprise.layout.Grid
import tz.co.asoft.enterprise.layout.Surface
import tz.co.asoft.enterprise.simplebar.SimpleBar
import tz.co.asoft.enterprise.web.*
import tz.co.asoft.*

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

fun RBuilder.SimpleSystemHomePage(content: SimpleSystemHomePageContent) = SimpleBar {
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

