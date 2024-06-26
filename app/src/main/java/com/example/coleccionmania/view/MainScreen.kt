package com.example.coleccionmania.view

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.coleccionmania.ProductsViewModel
import com.example.coleccionmania.model.Juegos
import com.example.coleccionmania.model.Product
import com.example.coleccionmania.navigation.AppScreens
import com.example.coleccionmania.navigation.ItemTabs
import kotlinx.coroutines.launch


@Composable
fun MainScreen(viewModel: ProductsViewModel, viewModelJuegos: JuegosViewModel, navHostController: NavHostController) {

        Column {

            TopBarItem()

            Box(modifier = Modifier.weight(1f)) {
                MovimientosTab(viewModel = viewModel, viewModelJuegos = viewModelJuegos, navHostController)
            }

        }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarItem(){
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Filled.Menu , contentDescription = null,
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
                    Text(text = "ColeccionMania", color = Color.White)
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

@Composable
fun BotonFlotante(navHostController: NavHostController){

        FloatingActionButton(
            onClick = {
                navHostController.navigate("product") // Reemplaza "camera_screen" con la ruta correcta a tu pantalla de cámara
            },

        ) {
            Icon(Icons.Default.Add, contentDescription = "Tomar foto")
        }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovimientosTab(viewModel: ProductsViewModel, viewModelJuegos: JuegosViewModel, navHostController: NavHostController){
    val tabs = listOf(
        ItemTabs.tab_amiibos,
        ItemTabs.tab_juegos
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { tabs.size } )
    Column {
        Tabs(tabs, pagerState)
        TabsContent(tabs, pagerState, viewModel,viewModelJuegos, navHostController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(tabs: List<ItemTabs>, pagerState: PagerState, viewModel: ProductsViewModel, viewModelJuegos: JuegosViewModel, navHostController: NavHostController) {
    HorizontalPager(
        state = pagerState,
        //pageCount = tabs.size
    ) {page ->
        tabs[page].screen(viewModel, viewModelJuegos, navHostController)
        
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(tabs: List<ItemTabs>, pagerState:  PagerState) {
    var selectedTab = pagerState.currentPage
    var scope = rememberCoroutineScope()
    TabRow(selectedTabIndex = selectedTab) {
        tabs.forEachIndexed { index, itemsTab ->
            Tab(
                selected = selectedTab == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(text = itemsTab.title) },
                icon = {
                    Icon(
                        if (index == selectedTab)
                            itemsTab.iconSelected
                        else
                            itemsTab.iconUnSelected, itemsTab.title
                    )
                }
            )
        }
    }
}


//Categorias
@Composable
fun Amiibos(viewModel: ProductsViewModel, viewModelJuegos: JuegosViewModel, navHostController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Busqueda(viewModel = viewModel)
        ListProduct(viewModel = viewModel, navHostController)
        //Icon(Icons.Outlined.Build, "Amiibos")
        //Text(text = "Amiibos2")//contenido de la pagina
    }
}

@Composable
fun Juegos(viewModel: ProductsViewModel, viewModelJuegos: JuegosViewModel, navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        BusquedaJuegos(viewModelJuegos = viewModelJuegos)
        ListJuegos(viewModelJuegos = viewModelJuegos, navHostController)
       // Icon(Icons.Outlined.AccountBox, "Juegos")
       // Text(text = "Juegos")//contenido de la pagina
    }
}

@Composable
fun BusquedaJuegos(viewModelJuegos: JuegosViewModel) {
    val busqueda = remember {
        mutableStateOf("")
    }
 //   val filteredJuegos by viewModelJuegos.filteredJuegos.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = busqueda.value,
            onValueChange = { newText ->
                busqueda.value = newText
                viewModelJuegos.filterJuegos(newText) // Llama al método de filtrado del ViewModel al cambiar el texto de búsqueda
            },
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
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search), // Especifica la acción de búsqueda para el botón de acción del teclado
            keyboardActions = KeyboardActions(onSearch = {
                viewModelJuegos.filterJuegos(busqueda.value) // Llama al método de filtrado del ViewModel cuando se presiona Enter
                keyboardController?.hide()//oculta el teclado
            }),
            //modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun Busqueda(viewModel: ProductsViewModel) {
    val busqueda = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = busqueda.value,
            onValueChange = { newText ->
                busqueda.value = newText
                viewModel.filterProducts(newText) // Llama al método de filtrado del ViewModel al cambiar el texto de búsqueda
            },
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
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search), // Especifica la acción de búsqueda para el botón de acción del teclado
            keyboardActions = KeyboardActions(onSearch = {
                viewModel.filterProducts(busqueda.value) // Llama al método de filtrado del ViewModel cuando se presiona Enter
                keyboardController?.hide()//oculta el teclado
            }),
            //modifier = Modifier.padding(8.dp)
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListProduct(viewModel: ProductsViewModel, navHostController: NavHostController) {

    val products by viewModel.filteredProducts.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }

    Scaffold(
        floatingActionButton = {
            BotonFlotante(navHostController)
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(products) { currentProduct ->
                    Producto(product = currentProduct) { productName, productDetail, productPrice, productImage ->
                        val encodedImage = Uri.encode(productImage)
                        navHostController.navigate("${AppScreens.MainScreen.route}/$productName/$productDetail/$productPrice/$encodedImage")
                    }

                }
                /* item {
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
        }*/
            }
        }
}

@Composable
fun Producto(product: Product, onClick:(String, String, String, String)->Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .wrapContentSize()
            .clickable {
                onClick(product.name, product.detail, product.price, product.image)

                //navController.navigate(route= "DetailScreen/$product")
                //   navController.navigate(route= "DetailScreen")
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
                //val imageVector: Painter = painterResource(id = product.image)
                Image(
                    //imageVector = Icons.Filled.AccountBox,
                    painter = rememberImagePainter(product.image),
                    contentDescription = null,
                    modifier = Modifier.size(140.dp)
                )
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = product.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = product.detail, fontSize = 15.sp)
                }
                Row(modifier = Modifier.padding(5.dp)) {
                    Text(text = "# ${product.id}", fontSize = 15.sp)
                }

            }
            Text(
                text = "$ ${product.price}",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                color = Color(0xFF4CAF50),
                fontSize = 24.sp,
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

@Composable
fun ListJuegos(viewModelJuegos: JuegosViewModel, navHostController: NavHostController) {

    val juegos by viewModelJuegos.filteredJuegos.collectAsState()

    LaunchedEffect(Unit) {
        viewModelJuegos.fetchJuegos()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(juegos){currentJuego->
            Juego(juego = currentJuego)// { productName, productDetail, productPrice, productImage ->
              //  val encodedImage = Uri.encode(productImage)
              //  navHostController.navigate("${AppScreens.MainScreen.route}/$productName/$productDetail/$productPrice/$encodedImage")
           // }

        }
    }
}

@Composable
fun Juego(juego: Juegos) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .wrapContentSize()
            .clickable {
                //onClick(product.name, product.detail, product.price, product.image)

                //navController.navigate(route= "DetailScreen/$product")
                //   navController.navigate(route= "DetailScreen")
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
                //val imageVector: Painter = painterResource(id = product.image)
                Image(
                    //imageVector = Icons.Filled.AccountBox,
                    painter = rememberImagePainter(juego.image),
                    contentDescription = null,
                    modifier = Modifier.size(140.dp)
                )
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = juego.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = juego.detail, fontSize = 15.sp)
                }
                Row(modifier = Modifier.padding(5.dp)) {
                    Text(text = "# ${juego.id}", fontSize = 15.sp)
                }

            }
            Text(
                text = "$ ${juego.price}",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                color = Color(0xFF4CAF50),
                fontSize = 24.sp,
                fontWeight = FontWeight.Black

            )
        }


    }
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
    val navController = rememberNavController()
    MainScreen(viewModel = ProductsViewModel(), viewModelJuegos = JuegosViewModel(),navController)
}
