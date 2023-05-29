package com.example.mymovieslist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mymovieslist.presentation.home.HomeRoute
import com.example.mymovieslist.presentation.screens.FavoriteScreen
import com.example.mymovieslist.presentation.screens.SearchScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = BottomNavItem.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(route = BottomNavItem.Home.route) {
            HomeRoute(hiltViewModel())
        }

        composable(route = BottomNavItem.Search.route) {
            SearchScreen()
        }

        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen()
        }
    }
}
