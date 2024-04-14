package com.example.coleccionmania.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset.Companion.Infinite
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coleccionmania.R
import com.example.coleccionmania.navigation.AppScreens
import com.squareup.wire.durationOfSeconds
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(5000)
        navController.popBackStack()
        navController.navigate(AppScreens.MainScreen.route)
    }
    Splash()
}

@Composable
fun Splash() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF070b31)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            var isVisible by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true) {
                isVisible = true
            }
            Image(
                modifier = Modifier.size(500.dp),
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "logo ColeccionMania"
            )
            Spacer(modifier = Modifier.height(25.dp))
            val opacityText by animateFloatAsState(
                targetValue = if (isVisible) 1f else 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1000), // Duración total del parpadeo
                    repeatMode = RepeatMode.Reverse, // Invertir el parpadeo
                )
            )
            Text(
                text = "Espere cargando...",
                color = Color.White,
                modifier = Modifier.alpha(opacityText)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    SplashScreen(navController)
}