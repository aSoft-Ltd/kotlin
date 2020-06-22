package tz.co.asoft.components.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun <reified P : Any, reified S : Any, T : AndroidComponent<P, S>> Parent.child(clazz: Class<T>, props: P? = null, inFrame: Int = frameId, noinline handler: T.() -> Unit = {}) {
    childFragment(clazz, props, handler).show(inFrame, clazz.canonicalName)
}

inline fun <reified P : Any, reified S : Any, T : AndroidComponent<P, S>> childFragment(clazz: Class<T>, props: P?, noinline handler: T.() -> Unit): T {
    val frag = clazz.newInstance() as T
    val p = props ?: P::class.java.newInstance()
    frag.realProps = p
    frag.realState = S::class.java.newInstance()
    frag.apply(handler)
    frag.arguments = Bundle().apply {
        append("props", frag.realProps)
    }
    return frag
}

fun FragmentManager.showFragment(id: Int, fragment: Fragment, backStack: String? = null) {
    beginTransaction().apply {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        val fragName: String? = backStack
        replace(id, fragment, null)
        fragName?.let { addToBackStack(it) }
        commitAllowingStateLoss()
    }
}