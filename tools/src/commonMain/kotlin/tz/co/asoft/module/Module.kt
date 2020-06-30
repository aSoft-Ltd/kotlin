package tz.co.asoft.module

abstract class Module {
    abstract val name: String
    abstract val author: String
    abstract val version: String
    abstract val mainSection: Section
    open var init = {}
    open var icon = "cloud"
    abstract val sections: Array<Section>

    val permits: Array<String>
        get() {
            var perms = arrayOf<String>()
            var secs = sections
            secs += mainSection
            secs.forEach { section ->
                section.permits.forEach { perm ->
                    if (!perms.contains(perm))
                        perms += perm
                }
            }
            return perms
        }

    abstract class Section {
        abstract val name: String
        abstract val permits: Array<String>
        open val isMenu: Boolean = true
        abstract val route: String
        abstract val component: Any
        open var show: suspend () -> Boolean = { true }
    }
}