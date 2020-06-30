package tz.co.asoft.components

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class Component : Fragment(), CoroutineScope by CoroutineScope(SupervisorJob() + Dispatchers.Main) {

}