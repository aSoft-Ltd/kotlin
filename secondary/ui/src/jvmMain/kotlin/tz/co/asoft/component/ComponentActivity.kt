package tz.co.asoft.component

import javafx.scene.Parent
import tornadofx.View
import tornadofx.vbox

abstract class ComponentActivity : View(), ParentContainer {
    override val root: Parent = vbox { }

    override fun onDock() {
        super.onDock()
        render()
    }
}
