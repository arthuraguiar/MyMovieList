package com.example.mymovieslist.presentation.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mymovieslist.presentation.navigation.Home
import com.example.mymovieslist.presentation.navigation.MainDestination
import com.example.mymovieslist.presentation.navigation.MainNavGraph
import com.example.mymovieslist.presentation.navigation.MainNavigationActions
import com.example.mymovieslist.presentation.navigation.bottomMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainerScreen(navController: NavHostController) {

    val navigationActions = remember(navController) {
        MainNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination = navBackStackEntry?.destination?.route ?: Home.route

    Scaffold(
        bottomBar = {
            RenderBottomBar(
                selectedDestination = selectedDestination,
                navigateToTopLevelDestination = navigationActions::navigateTo
            )
        }
    ) { innerPadding ->
        MainNavGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}

@Composable
private fun RenderBottomBar(
    selectedDestination: String,
    navigateToTopLevelDestination: (MainDestination) -> Unit
) {
    NavigationBar(
        Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.inverseOnSurface
    ) {
        bottomMenu.forEach { destination ->
            NavigationBarItem(
                selected = selectedDestination == destination.route,
                onClick = { navigateToTopLevelDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}