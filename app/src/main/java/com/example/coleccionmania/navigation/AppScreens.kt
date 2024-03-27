package com.example.coleccionmania.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens(route = "splash_screen")
    object MainScreen: AppScreens(route = "main_screen")
}