package com.example.trivial

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.trivial.data.QuizViewModel
import com.example.trivial.ui.about.AboutScreen
import com.example.trivial.ui.questions.QuestionsScreen
import com.example.trivial.ui.scores.ScoresScreen
import com.example.trivial.ui.settings.SettingsScreen
import com.example.trivial.ui.welcome.WelcomeScreen


@Composable
fun TrivialNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: QuizViewModel = hiltViewModel()

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
            QuestionsScreen(
                viewModel = viewModel,
                onNavigateToScores = {
                    navController.navigateSingleTopTo(Scores.route)
                }
            )

        }

        composable(route = Scores.route) {
            viewModel.updateScoresList()
            ScoresScreen(
                viewModel = viewModel,
            )
        }

        composable(route = Settings.route) {
            SettingsScreen()
        }

        composable(route = About.route) {
            AboutScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }