package tz.co.asoft.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import tz.co.asoft.components.CProps
import tz.co.asoft.components.CState

fun <P : CProps> Class<P>.newPropsInstance() = try {
    newInstance() as P
} catch (c: Throwable) {
    getDeclaredField("INSTANCE").get(null) as P
}

@Deprecated("Use clean props. Don't create like this")
fun <P : CProps, S : CState> Class<S>.newStateInstance(pClass: Class<P>? = null, props: P? = null) = try {
    if (props != null && pClass != null) {
        val ctr = getConstructor(pClass)
        ctr.newInstance(props)!!
    } else {
        throw Throwable("props is null")
    }
} catch (c: Throwable) {
    newInstance()!!
}

@Deprecated("Use showFragment from tz.co.asoft.components.android.showFragments")
fun FragmentManager.showFragment(id: Int, fragment: Fragment, backStack: String? = null) {
    beginTransaction().apply {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        replace(id, fragment, backStack)
        backStack?.let { addToBackStack(it) }
        commitAllowingStateLoss()
    }
}