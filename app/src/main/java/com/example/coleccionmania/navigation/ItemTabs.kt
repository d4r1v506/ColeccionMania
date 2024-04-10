package com.example.coleccionmania.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.coleccionmania.ProductsViewModel
import com.example.coleccionmania.view.Amiibos
import com.example.coleccionmania.view.Juegos

sealed class ItemTabs(
    var title: String,
    var iconSelected: ImageVector,
    var iconUnSelected: ImageVector,
    var screen: @Composable() (viewModel: ProductsViewModel, navHostController: NavHostController) -> Unit
) {
    object tab_amiibos : ItemTabs(
        "Amiibos",
        Icons.Filled.Build,
        Icons.Filled.Build,
        { viewModel, navHostController -> Amiibos(viewModel = viewModel, navHostController = navHostController) }
    )

    object tab_juegos : ItemTabs(
        "Juegos",
        Icons.Filled.AccountBox,
        Icons.Filled.AccountBox,
        { viewModel, navHostController -> Juegos(viewModel = viewModel, navHostController = navHostController) }
    )
}
