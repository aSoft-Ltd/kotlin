package tz.co.asoft.viewmodel

import tz.co.asoft.rx.LiveData

abstract class ViewModel<I, S>(initialState: S) : BaseViewModel() {
    val ui = LiveData(initialState)
    abstract suspend fun post(i: I)
}