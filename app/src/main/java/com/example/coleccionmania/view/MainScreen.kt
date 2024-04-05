package com.example.coleccionmania.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coleccionmania.model.Product


@Composable
fun MainScreen() {
    Column {
        // TopBar()
       // Categorias()

        Busqueda()
        ListProduct()
        //*  MyBottomBar()


    }
}


//Categorias
@Composable
fun Amiibos() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Icon(Icons.Outlined.Build, "Amiibos")
        Text(text = "Amiibos")
    }
}

@Composable
fun Juegos() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Icon(Icons.Outlined.AccountBox, "Juegos")
        Text(text = "Juegos")
    }
}

@Composable
fun Busqueda() {
    val busqueda = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = busqueda.value,
            onValueChange = { newText -> busqueda.value = newText },
            /*label = {
                Text("Busqueda", color = Color.Gray)
            },*/
            placeholder = {
                Text(text = "Busqueda")
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                )
            },
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            modifier = Modifier.padding(8.dp)

        )
    }
}

@Composable
fun ListProduct() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Producto(
                Product(
                    id = "1",
                    name = "Figura1",
                    detail = "figura nueva",
                    price = 85.00,
                    image = ""
                )
            )
            Divider()
            Producto(
                Product(
                    id = "2",
                    name = "Figura2",
                    detail = "figura nueva",
                    price = 85.00,
                    image = ""
                )
            )
        }
    }
}

@Composable
fun Producto(product: Product) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .wrapContentSize()
            .clickable {
                //navController.navigate(route= "DetailScreen/$itemIndex")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = null,
                    modifier = Modifier.size(140.dp)
                )
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = "texto1", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "texto2", fontSize = 18.sp)
                }
                Row(modifier = Modifier.padding(10.dp)) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null
                    )
                    Text(text = "Quito")
                }

            }
            Text(
                text = "$ 123",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                color = Color(0xFF4CAF50),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black

            )
        }


    }
    /*Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        ) {
        //https://assets.coincap.io/assets/icons/btc@2x.png
        Box(modifier = Modifier.padding(horizontal = 8.dp)) {

            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .padding(8.dp)
                    .size(30.dp)
            )

        }
        Column() {
            Text(
                text = product.name,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = product.detail,
                fontSize =14.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$${product.price}",
            fontSize = 14.sp,
            color = Color.Green.copy(alpha = 0.9f),
            //color = Color(0x007F0000),
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
    }
    Divider()*/
}

//--------------------
@Composable
fun Main(
    imageId: Array<Int>,
    names: Array<String>,
    ingredients: Array<String>,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = imageId.size

        items(itemCount) {
            ColumnItem(
                modifier,
                painter = imageId,
                title = names,
                ingredients = ingredients,
                itemIndex = it,
                navController = navController
            )
        }
    }

}

@Composable
fun ColumnItem(
    modifier: Modifier,
    painter: Array<Int>,
    title: Array<String>,
    ingredients: Array<String>,
    itemIndex: Int,
    navController: NavController
) {


    Card(
        modifier
            .padding(10.dp)
            .wrapContentSize()
            .clickable {
                navController.navigate(route = "DetailScreen/$itemIndex")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                painter = painterResource(id = painter[itemIndex]),
                contentDescription = title[itemIndex],
                modifier.size(140.dp)
            )
            Column(modifier.padding(12.dp)) {
                Text(text = title[itemIndex], fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = ingredients[itemIndex], fontSize = 18.sp)

            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainScreenPreview() {
    MainScreen()
}