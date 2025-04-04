package com.example.trivial

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.trivial.ui.welcome.WelcomeScreen


@Composable
fun TrivialNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Welcome.route,
        modifier = modifier
    ) {
        composable(route = Welcome.route) {
            WelcomeScreen {
                navController.navigateSingleTopTo(Questions.route)
            }
        }

        composable(route = Questions.route) {

        }

        composable(route = Scores.route) {

        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }