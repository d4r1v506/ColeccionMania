package com.example.coleccionmania.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(texto: String, navHostController: NavHostController){
  //  val navigationController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        topBar = {
            TopAppBar(
               navigationIcon = {
                   IconButton(onClick = { navHostController.navigateUp() }) {
                       Icon(
                           imageVector = Icons.Filled.KeyboardArrowLeft , contentDescription = null,
                           tint = Color.White
                       )
                   }

               },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart, // Reemplaza "YourIcon" con el nombre del icono que desees usar
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert, // Reemplaza "YourIcon" con el nombre del icono que desees usar
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFF070b31)),
                title = {
                    Text(text = "$texto", color = Color.White)
                }
            )
        },

        content = {
            Contenido()
        }
    )
}

@Composable
fun Contenido(){
    Text(text = "HOla mundo")
}

/*
@Preview(showSystemUi = true)
@Composable
fun PreviewTopBar(){
    TopBar("test")
}*/
