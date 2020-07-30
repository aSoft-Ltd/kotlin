package tz.co.asoft.enterprise.webpages

import react.RBuilder
import tz.co.asoft.Handler
import tz.co.asoft.enterprise.layout.Grid
import tz.co.asoft.enterprise.layout.Surface
import tz.co.asoft.enterprise.simplebar.SimpleBar
import tz.co.asoft.enterprise.web.*

class SimpleClientHomePageContent(
    val title: String,
    val menus: List<Handler>,
    val inspirationalImageUrl: String,
    val intro: Intro,
    val circleSection: PictureDetailGrid,
    val numOfTrusted: String,
    val footerSurface: Surface,
    val entityName: String
)

fun RBuilder.SimpleClientHomePage(content: SimpleClientHomePageContent) = SimpleBar {
    Grid(gap = "3em") {
        TransparentToolbar(content.title, content.menus)
        InspirationImage(content.inspirationalImageUrl)
        Introduction(content.intro)
        PictureDetailGrid(content.circleSection)
        TrustedBy(content.numOfTrusted)
        Surface(content.footerSurface) { FooterRibbon(content.entityName) }
    }
}