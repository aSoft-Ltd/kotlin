package tz.co.asoft.camera.viewmodel

import tz.co.asoft.camera.viewmodel.CaptureViewModel.Intent
import tz.co.asoft.camera.viewmodel.CaptureViewModel.State
import tz.co.asoft.viewmodel.ViewModel
import java.io.File

class CaptureViewModel : ViewModel<Intent, State>(State.Capturing) {
    sealed class State {
        object Capturing : State()
        class Previewing(val image: File) : State()
    }

    sealed class Intent {
        object Capture : Intent()
        class Preview(val image: File) : Intent()
    }

    override suspend fun post(i: Intent) = when (i) {
        Intent.Capture -> ui.value = (State.Capturing)
        is Intent.Preview -> ui.value = (State.Previewing(i.image))
    }
}