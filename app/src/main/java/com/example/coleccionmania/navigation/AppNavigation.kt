package com.example.coleccionmania.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coleccionmania.controller.Login
import com.example.coleccionmania.model.Product
import com.example.coleccionmania.view.ListProduct
import com.example.coleccionmania.view.LoginScreen
import com.example.coleccionmania.view.MainScreen
import com.example.coleccionmania.view.SplashScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
      //  startDestination = AppScreens.SplashScreen.route
        startDestination = AppScreens.MainScreen.route
    ){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(AppScreens.MainScreen.route){
           ListProduct()
        }
        composable(AppScreens.Login.route){
            LoginScreen()
        }
    }
}