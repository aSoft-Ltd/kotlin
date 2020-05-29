package tz.co.asoft.components.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import tz.co.asoft.ui.R
import kotlin.coroutines.CoroutineContext

open class ComponentActivity : AppCompatActivity(), Parent, CoroutineScope {
    protected val job = SupervisorJob()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    override val fManager get() = supportFragmentManager

    val ctx get() = applicationContext!!

    val app get() = application

    open val layoutId = R.layout.fragment_frame
    override val frameId = R.id.frame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        render()
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    open fun render(): Any = Unit
}