package tz.co.asoft.component

import javafx.scene.Parent

inline fun <reified P : Any, reified S : Any, T : JFXComponent<P, S>> ParentContainer.child(clazz: Class<T>, props: P? = null, inNode: Parent = root, noinline handler: T.() -> Unit = {}) {
    childFragment(clazz, props, handler).show(inNode)
}

inline fun <reified P : Any, reified S : Any, T : JFXComponent<P, S>> childFragment(clazz: Class<T>, props: P?, noinline handler: T.() -> Unit): T {
    val frag = clazz.newInstance() as T
    frag.realProps = props ?: P::class.java.newInstance()
    if (frag.realState == null) {
        frag.realState = S::class.java.newInstance()
    }
    frag.apply(handler)
    return frag
}