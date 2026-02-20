package com.slurman

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.slurman.ui.dashboard.DashboardScreen
import com.slurman.ui.dashboard.DashboardViewModel
import com.slurman.ui.login.LoginScreen
import com.slurman.ui.login.LoginViewModel

sealed class AppRoute(val route: String) {
    data object Login : AppRoute("login")
    data object Dashboard : AppRoute("dashboard")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoute.Login.route
    ) {
        composable(AppRoute.Login.route) {
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(
                viewModel = viewModel,
                onNavigateToDashboard = {
                    navController.navigate(AppRoute.Dashboard.route) {
                        popUpTo(AppRoute.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(AppRoute.Dashboard.route) {
            val viewModel: DashboardViewModel = viewModel()
            DashboardScreen(viewModel = viewModel)
        }
    }
}
