package com.example.coleccionmania.navigation

sealed class AppScreens(val route: String) {
    data object SplashScreen: AppScreens("splash_screen")
    data object MainScreen: AppScreens("main_screen")
    data object Login: AppScreens("login")


}