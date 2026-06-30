package com.sarang.securepay.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SecurePayApp() {
    val navController = rememberNavController()
    AppNavHost(navController = navController)
}
