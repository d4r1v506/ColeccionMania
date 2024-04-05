package com.example.coleccionmania.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.coleccionmania.view.Amiibos
import com.example.coleccionmania.view.Juegos

sealed class ItemTabs(
    var title: String,
    var iconSelected: ImageVector,
    var iconUnSelected: ImageVector,
  //  var screen: @Composable() -> Unit
) {
 /*   object tab_amiibos : ItemTabs(
        "Amiibos",
        Icons.Filled.Build,
        Icons.Outlined.Build,
        { Amiibos() }
    )

    object tab_juegos : ItemTabs(
        "Juegos",
        Icons.Filled.AccountBox,
        Icons.Outlined.AccountBox,
        { Juegos() }
    )*/
}
