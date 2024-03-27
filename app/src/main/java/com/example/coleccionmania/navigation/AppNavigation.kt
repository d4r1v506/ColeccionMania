package com.example.coleccionmania.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coleccionmania.model.Product
import com.example.coleccionmania.view.MainScreen
import com.example.coleccionmania.view.SplashScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(AppScreens.MainScreen.route){
            MainScreen(
                Product(
                id = "2",
                name ="Figura2",
                detail = "figura nueva",
                price = 85.00,
                image = ""
            )
            )
        }
    }
}