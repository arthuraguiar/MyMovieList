package com.example.mymovieslist.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.mymovieslist.presentation.screens.MainContainerScreen
import com.example.theme.MyMoviesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MyMoviesApp() {
    MyMoviesTheme {
        val navController = rememberNavController()

        StatusBarIcons()

        MainContainerScreen(navController)
    }
}

@Composable
private fun StatusBarIcons() {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = false)
    }
}