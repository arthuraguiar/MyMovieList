package com.example.mymovieslist.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mymovieslist.presentation.screens.FavoriteScreen
import com.example.mymovieslist.presentation.screens.HomeScreen
import com.example.mymovieslist.presentation.screens.MainContainerScreen
import com.example.mymovieslist.presentation.screens.SearchScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: MainDestination = Home
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {

        composable(route = Home.route) {
            HomeScreen(hiltViewModel())
        }

        composable(route = Search.route) {
            SearchScreen()
        }

        composable(route = Favorite.route) {
            FavoriteScreen()
        }
    }
}

interface Router {
    val route: String
}

interface MainDestination : Router {
    val icon: ImageVector
}

object Home : MainDestination {
    override val icon = Icons.Outlined.Home
    override val route = "home"
}

object Search : MainDestination {
    override val icon = Icons.Outlined.Search
    override val route = "search"
}

object Favorite : MainDestination {
    override val icon = Icons.Outlined.Favorite
    override val route = "favorite"
}

val bottomMenu = listOf(Home, Search, Favorite)

class MainNavigationActions(private val navController: NavHostController) {

    fun navigateTo(destination: MainDestination) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}