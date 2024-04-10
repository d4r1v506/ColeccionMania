package com.example.coleccionmania.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coleccionmania.ProductsViewModel
import com.example.coleccionmania.view.Detalle
import com.example.coleccionmania.view.ListProduct
import com.example.coleccionmania.view.LoginScreen
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
           ListProduct(viewModel = ProductsViewModel(), navController)
        }
        composable(AppScreens.Login.route){
            LoginScreen()
        }

        composable("${AppScreens.MainScreen.route}/{productName}/{productDetail}/{productPrice}/{productImage}") { backStackEntry ->
            Detalle(
                productName = backStackEntry.arguments?.getString("productName") ?: "",
                productDetail = backStackEntry.arguments?.getString("productDetail") ?: "",
                productPrice = backStackEntry.arguments?.getString("productPrice") ?: "",
                productImage = Uri.decode(backStackEntry.arguments?.getString("productImage") ?: "")
            )
        }
    }
}