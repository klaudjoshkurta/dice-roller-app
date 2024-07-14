package com.klaudjoshkurta.thoughts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klaudjoshkurta.thoughts.ui.home.HomeScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        /** Home screen */
        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}