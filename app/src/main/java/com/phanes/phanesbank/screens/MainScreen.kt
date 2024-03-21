package com.phanes.phanesbank.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Apartment
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.phanes.phanesbank.ui.BottomNavigationBar

@Composable
fun MainScreen(

) {

    val navHostController = rememberNavController()

    val backStackEntry by navHostController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {

        },
        bottomBar = {
            BottomNavigationBar(
                navHostController = navHostController
            )
        },
        floatingActionButton = {

            when(backStackEntry?.destination?.route) {
                NavRoutes.Home.route -> {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        TextButton(
                            colors = ButtonDefaults.buttonColors(),
                            shape = RoundedCornerShape(50),
                            onClick = { /*TODO*/ },
                        ) {
                            Icon(imageVector = Icons.Rounded.Apartment, contentDescription = "Invest")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "Invest")
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        TextButton(
                            shape = RoundedCornerShape(50),
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors()
                        ) {
                            Icon(imageVector = Icons.Rounded.Apartment, contentDescription = "Invest")
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "Fund")
                        }
                    }
                }

                NavRoutes.Groups.route -> {
                    FloatingActionButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Create Group")
                    }
                }

                NavRoutes.Loans.route -> {
                    TextButton(
                        shape = RoundedCornerShape(50),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors()
                    ) {
                        Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = "Apply for Loan")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Apply")
                    }
                }
                else -> {}
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            PhanesNavHost(navHostController = navHostController)
        }
    }
}