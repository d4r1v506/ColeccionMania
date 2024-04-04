package com.example.coleccionmania.navigation

import com.example.coleccionmania.R

sealed class ItemsMenu (
    val icon: Int,
    val title: String,
    val ruta: String
){
    object MainScreen: ItemsMenu(R.drawable.baseline_home_filled_24,"Inicio","MainScreen")
    object Login: ItemsMenu(R.drawable.baseline_person_24,"Perfil","Login")
    object Favoritos: ItemsMenu(R.drawable.baseline_star_rate_24,"Favoritos","Favoritos")
    object Shoping: ItemsMenu(R.drawable.baseline_shopping_cart_24,"Shoping","Shoping")
}