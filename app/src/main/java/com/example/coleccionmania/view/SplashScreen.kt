package com.example.coleccionmania.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.coleccionmania.R
import com.example.coleccionmania.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController){
    LaunchedEffect(key1 = true) {
        delay(5000)
        navController.popBackStack()
        navController.navigate(AppScreens.MainScreen.route)

    }
    Splash()
}

@Composable
fun Splash(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo ColeccionMania"
        )
       Text(text = "Espere cargando...")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview(){
  //  SplashScreen()
}