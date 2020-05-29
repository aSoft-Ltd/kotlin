package tz.co.asoft.components.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Parent {
    val fManager: FragmentManager
    val frameId: Int

    fun Fragment.show(id: Int = frameId, backStack: String? = null) = fManager.showFragment(id, this, backStack)
}