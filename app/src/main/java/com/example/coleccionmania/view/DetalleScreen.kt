package com.example.coleccionmania.view

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
//import com.example.coleccionmania.MyBottomBar
import com.example.coleccionmania.R
import com.example.coleccionmania.model.Product
import com.example.coleccionmania.navigation.AppScreens
import com.example.coleccionmania.navigation.TopBar
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun Detalle(productName: String, productDetail: String, productPrice: String, productImage: String, navHostController: NavHostController){

    Column {
        TopBar("Detalle")
        CarruselCard(productImage)
        Descripcion(productName, productDetail, productPrice, productImage, navHostController)
    }

   /* TopBar()
    MyBottomBar()*/
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarruselCard(productImage: String) {

    val sliderList = listOf(
        productImage,
        productImage,
        productImage,
        productImage
        //"https://picsum.photos/id/237/200/300",
       // "https://picsum.photos/seed/picsum/200/300",
       // "https://picsum.photos/200/300?grayscale"
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { sliderList.size } // Reemplaza 3 con el número real de páginas
    )
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        }) {
            Icon(Icons.Default.KeyboardArrowLeft, null)
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .height(350.dp)
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 65.dp)
        ) { page ->
            Card(shape = RoundedCornerShape(10.dp),
                modifier = Modifier.graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    alpha = lerp(
                        start = 0.7f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                }
            ) {
                /*AsyncImage(
                     url = sliderList[page],
                     contentDescription = null,
                     modifier = Modifier.fillMaxSize()
                 )*/
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(sliderList[page])
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    error = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.Crop,

                    modifier = Modifier.fillMaxSize()
                )

            }
        }
        IconButton(onClick = {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }) {
            Icon(Icons.Default.KeyboardArrowRight, null)
        }
    }
    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(sliderList.size){
                it-> val color = if(pagerState.currentPage == it) Color.DarkGray else Color.LightGray
            Box(modifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .size(20.dp)
                .background(color)
                .clickable {
                    scope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                }
            )
        }
    }
}

@Composable
fun Descripcion(productName: String, productDetail: String, productPrice: String, productImage: String, navHostController: NavHostController){

        Card(
            modifier = Modifier
                .padding(10.dp)
                .wrapContentSize(),
            colors = CardDefaults.cardColors(
            containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)) {
            Text(
                text = "Nombre",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold)
            Text(text =  productName)
            Text(text = "Descripción",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold)
            Text(text =  productDetail)
            Text(text = "Precio",
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold)
            Text(text = "$$productPrice",
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.Black)
        }

       // Spacer(modifier = Modifier.weight(1f))
            val encodedImage = Uri.encode(productImage)
        Button(
            onClick = { navHostController.navigate("${AppScreens.PedidoScreen.route}/$productName/$productPrice/$encodedImage")},
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFc33d42))
        ) {
            Text(text = "Comprar", fontSize = 15.sp)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCarrusel() {
    val navController = rememberNavController()
    Detalle("","","","", navController)
}
