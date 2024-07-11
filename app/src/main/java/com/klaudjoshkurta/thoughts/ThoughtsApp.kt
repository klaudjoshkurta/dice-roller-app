package com.klaudjoshkurta.thoughts

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.klaudjoshkurta.thoughts.ui.navigation.AppNavigation
import com.klaudjoshkurta.thoughts.ui.navigation.Screen

@Composable
fun ThoughtsApp() {
    val navController = rememberNavController()
    AppNavigation(
        navController = navController,
        startDestination = Screen.Home.route
    )
}