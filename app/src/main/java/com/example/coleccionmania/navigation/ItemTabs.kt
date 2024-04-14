package com.example.coleccionmania.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.coleccionmania.ProductsViewModel
import com.example.coleccionmania.R
import com.example.coleccionmania.view.Amiibos
import com.example.coleccionmania.view.Juegos
import com.example.coleccionmania.view.JuegosViewModel

sealed class ItemTabs(
    var title: String,
    var iconSelected: ImageVector,
    var iconUnSelected: ImageVector,
    var screen: @Composable() (viewModel: ProductsViewModel, viewModelJuegos: JuegosViewModel, navHostController: NavHostController) -> Unit
) {
    object tab_amiibos : ItemTabs(
        "amiibos",
       Icons.Default.Face,
        Icons.Default.Face,
        { viewModel,viewModelJuegos, navHostController -> Amiibos(viewModel = viewModel,viewModelJuegos = viewModelJuegos, navHostController = navHostController) }
    )

    object tab_juegos : ItemTabs(
        "juegos",
        Icons.Filled.AccountBox,
        Icons.Filled.AccountBox,
        { viewModel,viewModelJuegos, navHostController -> Juegos(viewModel = viewModel, viewModelJuegos = viewModelJuegos, navHostController = navHostController) }
    )
}
