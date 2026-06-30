package com.sarang.securepay.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            // Splash Screen placeholder
        }
        composable(Screen.Login.route) {
            // Login Screen placeholder
        }
        composable(Screen.Register.route) {
            // Register Screen placeholder
        }
        composable(Screen.Dashboard.route) {
            // Dashboard Screen placeholder
        }
        composable(Screen.Wallet.route) {
            // Wallet Screen placeholder
        }
        composable(Screen.Transfer.route) {
            // Transfer Screen placeholder
        }
        composable(Screen.History.route) {
            // History Screen placeholder
        }
        composable(Screen.Profile.route) {
            // Profile Screen placeholder
        }
        composable(Screen.Settings.route) {
            // Settings Screen placeholder
        }
    }
}
