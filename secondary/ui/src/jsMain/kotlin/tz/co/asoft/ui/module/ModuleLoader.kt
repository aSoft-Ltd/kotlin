package tz.co.asoft.ui.module

fun loadJsModule(jsModule: dynamic): Module = object : Module() {
    override val name = jsModule.name
    override val author = jsModule.author
    override val version = jsModule.version
    override val mainSection = loadModuleSection(jsModule.mainSection)
    private var secs = mutableListOf<Section>()

    init {
        jsModule.sections.unsafeCast<Array<dynamic>>().forEach {
            secs.add(loadModuleSection(it))
        }
        if (jsModule.icon != undefined) {
            icon = jsModule.icon
        }
    }

    override val sections: Array<Section>
        get() = secs.toTypedArray()
}

private fun loadModuleSection(jsSection: dynamic): Module.Section = object : Module.Section() {
    override val name = jsSection.name
    override val permits = jsSection.permits
    override val isMenu = if (jsSection.isMenu != null) jsSection.isMenu else true
    override val route = jsSection.route
    override val component = jsSection.component
}