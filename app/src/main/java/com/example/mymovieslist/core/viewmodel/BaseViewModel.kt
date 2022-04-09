package com.example.mymovieslist.core.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mymovieslist.core.viewmodel.state.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<STATE: ScreenState>(
    initState: STATE
) : ViewModel(){

    private val state = MutableStateFlow(initState)
    val screenState: StateFlow<STATE> = state

    fun setState(screenState: (STATE) -> STATE){
        state.update(screenState)
    }
}