package com.example.mymovieslist.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mymovieslist.core.extensions.getCurrentRoute

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Favorite,
    )

    NavigationBar {
        items.forEach { item ->
            NavigationItem(item = item, navController = navController)
        }
    }
}

@Composable
fun RowScope.NavigationItem(item: BottomNavItem, navController: NavController) {
    val selected = navController.getCurrentRoute() == item.route

    NavigationBarItem(
        selected = selected,
        onClick = navigate(item, navController),
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = null
            )
        }
    )
}

@Composable
fun navigate(item: BottomNavItem, navController: NavController): (() -> Unit) = {
    navController.navigate(item.route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) { saveState = true }
        }
        launchSingleTop = true
        restoreState = true
    }
}
