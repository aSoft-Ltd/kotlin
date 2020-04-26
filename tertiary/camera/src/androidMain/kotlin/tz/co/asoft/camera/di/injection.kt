package tz.co.asoft.camera.di

import tz.co.asoft.camera.viewmodel.CaptureViewModel

object injection {
    object viewModel {
        fun capture() = CaptureViewModel()
    }
}