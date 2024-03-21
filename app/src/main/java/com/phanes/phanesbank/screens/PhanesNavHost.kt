package com.phanes.phanesbank.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun PhanesNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            Home(
                navHostController = navHostController
            )
        }

        composable(NavRoutes.Groups.route) {
            Groups()
        }

        composable(NavRoutes.Loans.route) {
            Loans()
        }

        composable(NavRoutes.Wallet.route) {
            Wallet()
        }
    }
}