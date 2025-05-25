package com.rajedev.comp.ui.launch

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rajedev.comp.R
import kotlinx.serialization.Serializable

/**
 * Copyright (c) 2025 /LiveRamp, All rights reserved.
 * Created by Rajendhiran Easu on 24/05/25.
 * Description: This file defines the screens and components used in the Compose
 * Component application.
 */

@Serializable
sealed class Screens(
    val route: String,
    @StringRes val titleId: Int = R.string.app_name
) {
    @Serializable
    data object Home : Screens(route = "home_screen", titleId = R.string.home_screen)

    @Serializable
    data object SearchComponent :
        Screens(route = "search_component_screen", titleId = R.string.search_component)

    companion object {
        fun fromRoute(route: String?): Screens {
            return when (route) {
                Home.route -> Home
                SearchComponent.route -> SearchComponent
                else -> Home // Default to Home if no match
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/") ?: Screens.Home.route
}

fun reusableComponents(): List<Component> {
    return listOf(buildComponents {
        cId = "searchComponent"
        screens = Screens.SearchComponent
        componentResId = Icons.Default.Search
    })
}

data class Component(
    var cId: String = "",
    var screens: Screens = Screens.Home,
    var componentResId: ImageVector = Icons.Default.Home,
)

fun buildComponents(comp: Component.() -> Unit): Component {
    val component = Component()
    component.comp()
    return component
}
