package com.phanes.phanesbank.screens

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

sealed class NavRoutes(
    val route: String,
    val label: String
) {
    data object Home: NavRoutes("home", "Home")
    data object Groups: NavRoutes(route = "groups", "Groups")
    data object Loans: NavRoutes(route = "loans", "Loans")
    data object Wallet: NavRoutes(route = "wallet", "Wallet")
}

object UseNavigate {
    fun navigate(navHostController: NavHostController, destination: NavRoutes) {
        navHostController.navigate(destination.route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigate(navHostController: NavHostController, destination: String) {
        navHostController.navigate(destination) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}