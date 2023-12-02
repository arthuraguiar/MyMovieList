package com.example.mymovieslist.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val icon: ImageVector,
    val title: String,
    val route: String,
) {
    data object Home : BottomNavItem(
        title = "home",
        route = "home",
        icon = Icons.Outlined.Home,
    )

    data object Search : BottomNavItem(
        title = "search",
        route = "search",
        icon = Icons.Outlined.Search,
    )

    data object Favorite : BottomNavItem(
        title = "favorite",
        route = "favorite",
        icon = Icons.Outlined.Favorite,
    )
}
