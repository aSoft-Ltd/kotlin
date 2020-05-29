package tz.co.asoft.component

import javafx.scene.Node
import javafx.scene.Parent
import tornadofx.replaceChildren

interface ParentContainer {
    val root: Parent
    fun <T : JFXComponent<*, *>> T.show(inNode: Node) {
        inNode.replaceChildren {
            add(this@show)
        }
    }

    fun render(): Any
}