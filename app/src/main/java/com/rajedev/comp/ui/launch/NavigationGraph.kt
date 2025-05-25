package com.rajedev.comp.ui.launch

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rajedev.comp.component.SearchDropDownScreen

/**
 * Copyright (c) 2025 /LiveRamp, All rights reserved.
 * Created by Rajendhiran Easu on 24/05/25.
 * Description: This is the navigation graph for the Compose Component application.
 */

@Composable
fun NavigationGraph(navController: NavHostController, initialScreen: String) {
    NavHost(navController, startDestination = initialScreen) {

        val navigateTo: (Screens) -> Unit = {
            navController.navigate(it.route)
        }

        composable(Screens.Home.route) {
            HomeScreen(navigateTo)
        }
        composable(Screens.SearchComponent.route) {
            SearchDropDownScreen(navigateTo)
        }
    }
}
