package com.example.mymovieslist.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.mymovieslist.presentation.navigation.AppNavHost
import com.example.mymovieslist.presentation.navigation.BottomNavigation
import com.example.theme.MyMoviesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMoviesApp() {
    MyMoviesTheme {

        StatusBarIcons()

        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigation(navController)
            }
        ) { innerPadding ->
            AppNavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}

@Composable
private fun StatusBarIcons() {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = false)
    }
}
