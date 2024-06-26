package com.example.coleccionmania

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.coleccionmania.navigation.AppNavigation
import com.example.coleccionmania.ui.theme.ColeccionManiaTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ProductsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColeccionManiaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //al navController = rememberNavController()
                    AppNavigation()
                    //Camera()

                  // SplashScreen(navController)
                   // MainScreen(viewModel, navHostController = nav)
                   // Detalle()
                  //*  ListProduct(viewModel = viewModel)
                    //DetallPedido()
                    //CarruselCard()
                    //com.example.coleccionmania.view.MainScreen()
                }
            }
        }
    }
}

/*@Composable
fun MyBottomBar(){
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val select = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.DarkGray
            ) {
                IconButton(
                    onClick = {
                        select.value = Icons.Default.Home
                        navigationController.navigate(AppScreens.MainScreen.route){
                            popUpTo(0)
                        }
                    },

                    modifier = Modifier.weight(1f)) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_home_filled_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)

                    )
                   // Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(26.dp),

                }
                IconButton(
                    onClick = {
                        select.value = Icons.Default.AccountCircle
                        navigationController.navigate(AppScreens.Login.route){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_star_rate_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)

                    )
                }
                IconButton(
                    onClick = {
                        select.value = Icons.Default.AccountCircle
                        navigationController.navigate(AppScreens.Login.route){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)

                    )
                }
                IconButton(
                    onClick = {
                        select.value = Icons.Default.AccountCircle
                        navigationController.navigate(AppScreens.Login.route){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(50.dp)

                    )
                }
               /* Box(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                    contentAlignment = Alignment.Center){
                    FloatingActionButton(onClick = { Toast.makeText(context, "open botton shep", Toast.LENGTH_SHORT).show() }) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color.Green)
                    }
                }*/
            }
        }
    )
    {
        paddingValues->
        NavHost(navController = navigationController,
            startDestination = AppScreens.MainScreen.route,
            modifier = Modifier.padding(paddingValues)){
            composable(AppScreens.MainScreen.route){ MainScreen() }
            composable(AppScreens.Login.route){LoginScreen()}

        }
    }
}

@Preview
@Composable
fun MyBottonBarPreview(){
    MyBottomBar()
}
*/


