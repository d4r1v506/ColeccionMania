package com.example.coleccionmania.navigation

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coleccionmania.ProductsViewModel
import com.example.coleccionmania.view.BottomNavItem
import com.example.coleccionmania.view.DetallPedido
import com.example.coleccionmania.view.Detalle
import com.example.coleccionmania.view.JuegosViewModel
import com.example.coleccionmania.view.LoginScreen
import com.example.coleccionmania.view.MainScreen
import com.example.coleccionmania.view.SplashScreen

/*@Composable
fun HomeScreen(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
       Text("HOME")
    }
}

@Composable
fun FavouritesScreen(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text("Favourites Screen")
    }
}

@Composable
fun PerfilScreen(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text("Perfil Screen")
    }
}*/

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
        //startDestination = BottomNavItem.Home.route
    ){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(AppScreens.MainScreen.route){
            MainScreen(viewModel = ProductsViewModel(),viewModelJuegos = JuegosViewModel(), navController)
            //HomeScreen()
        }
     /*   composable(BottomNavItem.Favourites.route){
            //MainScreen(viewModel = ProductsViewModel(),viewModelJuegos = JuegosViewModel(), navController)
            //FavouritesScreen()
        }*/
       /* composable(AppScreens.Login.route) {
            LoginScreen()
            //PerfilScreen()
        }*/

        composable("${AppScreens.MainScreen.route}/{productName}/{productDetail}/{productPrice}/{productImage}") { backStackEntry ->
            Detalle(
                productName = backStackEntry.arguments?.getString("productName") ?: "",
                productDetail = backStackEntry.arguments?.getString("productDetail") ?: "",
                productPrice = backStackEntry.arguments?.getString("productPrice") ?: "",
                productImage = Uri.decode(backStackEntry.arguments?.getString("productImage") ?: ""),
                navController
            )
        }

        composable("${AppScreens.PedidoScreen.route}/{productName}/{productPrice}/{productImage}") { backStackEntry ->
            DetallPedido(
                productName = backStackEntry.arguments?.getString("productName") ?: "",
                productPrice = backStackEntry.arguments?.getString("productPrice") ?: "",
                productImage = Uri.decode(backStackEntry.arguments?.getString("productImage") ?: ""),
            )
        }
    }
}

/*@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomTabBar(navController = navController)}
    ) {
        AppNavigation(navController)
    }
}*/

@Composable
fun BottomTabBar(navController: NavHostController){
    val tabBarItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favourites,
        BottomNavItem.Perfil,
    )
    BottomAppBar {
        val navBackStack by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStack?.destination?.route

        tabBarItems.forEach{barItem->
            val isSelected = currentRoute == barItem.route
            NavigationBarItem(
                selected = isSelected,
                label = {
                        Text(text = barItem.title)
                },
                onClick = {
                          navController.navigate(barItem.route)
                },
                icon = {
                    Icon(
                        imageVector = if(isSelected) barItem.selectedIcon else barItem.unselectedIcon,
                        contentDescription = barItem.title)
                }
            )

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewMainScreen(){
  //  MainScreen()
}