package com.example.myquoteapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myquoteapp.QuoteViewModel
import com.example.myquoteapp.ui.screens.*


@ExperimentalMaterial3Api
@Composable
fun AppNavGraph(navController: NavHostController) {
    val viewModel: QuoteViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(
                onGetStartedClick = {
                    navController.navigate("home") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                onSettingsClick = {}
            )
        }
        composable("home") {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable("quote_list") {
            QuoteListScreen(navController = navController, viewModel = viewModel)
        }
        composable("category_list") {
            QuoteCategoryScreen(navController = navController)
        }
        composable("profile") {
            Surface(modifier = Modifier.fillMaxSize()) {
                Text("Profile Screen Coming Soon", style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}
