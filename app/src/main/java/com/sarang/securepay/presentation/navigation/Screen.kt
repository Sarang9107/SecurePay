package com.sarang.securepay.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Dashboard : Screen("dashboard")
    object Wallet : Screen("wallet")
    object Transfer : Screen("transfer")
    object History : Screen("history")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
}
