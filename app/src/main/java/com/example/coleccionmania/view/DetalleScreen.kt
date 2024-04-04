package com.example.coleccionmania.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.fontscaling.MathUtils.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.coleccionmania.MyBottomBar
import com.example.coleccionmania.R
import com.example.coleccionmania.navigation.TopBar
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun Detalle(){

    Column {
        CarruselCard()
        Descripcion()
    }

   /* TopBar()
    MyBottomBar()*/
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarruselCard() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { 3 } // Reemplaza 3 con el número real de páginas
    )
    val sliderList = listOf(
        "https://picsum.photos/id/237/200/300",
        "https://picsum.photos/seed/picsum/200/300",
        "https://picsum.photos/200/300?grayscale"
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
fun Descripcion(){
    Column(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
        Text(text = "Descripción", color = Color(0xFF3F51B5))
        Text(text = "teasdaksdjnasjdkanskdjnasjdnaskjdnasjkdnasjkdnajks")
        Text(text = "Precio")
        Text(text = "$123", color = Color.Green)
       // Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Comprar", fontSize = 15.sp)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCarrusel() {
    Detalle()
}
