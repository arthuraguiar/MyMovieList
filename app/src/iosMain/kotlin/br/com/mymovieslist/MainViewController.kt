package br.com.mymovieslist

import androidx.compose.ui.window.ComposeUIViewController
import br.com.mymovieslist.di.initKoin
import br.com.mymovieslist.presentation.MyMoviesApp
import platform.UIKit.UIViewController

private var koinInitialized = false

fun MainViewController(): UIViewController {
    if (!koinInitialized) {
        initKoin()
        koinInitialized = true
    }
    return ComposeUIViewController {
        MyMoviesApp()
    }
}
