package com.klaudjoshkurta.thoughts.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klaudjoshkurta.thoughts.ui.home.HomeScreen
import com.klaudjoshkurta.thoughts.ui.input.FullInputScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object FullScreenInput : Screen("full_screen_input")
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
        composable(Screen.Home.route) {
            HomeScreen(
                onFullScreenClick = { navController.navigate(Screen.FullScreenInput.route) }
            )
        }
        composable(Screen.FullScreenInput.route) {
            FullInputScreen(
                onCloseClicked = {
                    navController.popBackStack()
                    navController.navigateUp()
                }
            )
        }
    }
}