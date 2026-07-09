package br.com.mymovieslist.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.mymovielist.theme.MyMoviesTheme
import br.com.mymovieslist.presentation.navigation.AppNavHost
import br.com.mymovieslist.presentation.navigation.BottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyMoviesApp() {
    MyMoviesTheme {

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