package tz.co.asoft

import react.RBuilder

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

fun RBuilder.SimpleClientHomePage(content: SimpleClientHomePageContent) = Scroller {
    Grid(gap = "3em") {
        TransparentToolbar(content.title, content.menus)
        InspirationImage(content.inspirationalImageUrl)
        Introduction(content.intro)
        PictureDetailGrid(content.circleSection)
        TrustedBy(content.numOfTrusted)
        Surface(content.footerSurface) { FooterRibbon(content.entityName) }
    }
}