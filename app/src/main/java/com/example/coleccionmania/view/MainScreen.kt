package com.example.coleccionmania.view
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coleccionmania.model.Product
import com.example.coleccionmania.navigation.ItemsMenu
import com.example.coleccionmania.navigation.MyBottomBar
import com.example.coleccionmania.navigation.TopBar


@Composable
fun MainScreen(){
    Column {
       // TopBar()
      //  Categorias()

        Busqueda()
        ListProduct()
     //*  MyBottomBar()




    }
}



@Composable
fun Categorias(){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()) {
        Text(text = "Figuras")
        Text(text = "Juegos")
        Text(text = "Colección")
    }
}
@Composable
fun Busqueda(){
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
fun ListProduct(){

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
        Producto(
            Product(
                id = "1",
                name ="Figura1",
                detail = "figura nueva",
                price = 85.00,
                image = ""
            )
        )
        Divider()
        Producto(
            Product(
                id = "2",
                name ="Figura2",
                detail = "figura nueva",
                price = 85.00,
                image = ""
            )
        )
        }
    }
}

@Composable
fun Producto(product: Product){

        Row(
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
        Divider()
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainScreenPreview(){
        MainScreen()
}