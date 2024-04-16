package com.example.coleccionmania.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.coleccionmania.navigation.TopBar
import kotlinx.coroutines.launch

@Composable
fun DetallPedido(productName: String, productPrice: String, productImage: String, navHostController: NavHostController) {

    Column {
        TopBar("Mi Pedido", navHostController)
        Pedido(productName, productPrice, productImage, navHostController)
    }
}

@Composable
fun Pedido(productName: String, productPrice: String, productImage: String, navHostController: NavHostController) {

    var selectedPago = remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var aceptarDialog by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var showBottomSheetScaffold by rememberSaveable { mutableStateOf(false) }
    val showModalBottomSheet = rememberSaveable { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 60.dp)
            ) {
                Image(
                    //painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    painter = rememberImagePainter(productImage),
                    contentDescription = "logo ColeccionMania",
                    modifier = Modifier.size(160.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                // .padding(vertical = 8.dp),
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Text(
                            text = "Nombre del producto",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )
                        Divider()
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text =  productName)

                        Text(text = "Valor:  ", fontWeight = FontWeight.Bold, color = Color.DarkGray)
                        Text(
                            text = "$${productPrice}",
                            color = Color(0xFF4CAF50),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { showDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = null,
                            tint = Color(0xFF4CAF50),
                        )
                    }
                    Text(text = "Código Promocional", fontWeight = FontWeight.Bold)
                }
                AnimatedVisibility (aceptarDialog == true) {
                    Text(text = "Código: ${textFieldValue.text.uppercase()}", Modifier.padding(15.dp))
                }
            }

            AnimatedVisibility (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text("Ingrese Código:")

                    },
                    text = {
                        Column {
                            TextField(
                                value = textFieldValue,
                                onValueChange = { textFieldValue = it }
                            )
                        }
                    },
                    confirmButton = {
                        Button(onClick = {
                            aceptarDialog = true
                            showDialog = false
                        }) {
                            Text("Aceptar")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("Cancelar")
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
           /* Text(text = "Valor:  ", fontWeight = FontWeight.Bold, color = Color.DarkGray)
            Text(
                text = "$${productPrice}",
                color = Color(0xFF4CAF50),
                fontSize = 25.sp,
                fontWeight = FontWeight.Black
            )*/


            Spacer(modifier = Modifier.height(35.dp))

            Button(
                onClick = { showModalBottomSheet.value = !showModalBottomSheet.value },
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
            ) {
                Text(text =
                if(selectedPago.value.isNotEmpty()){
                    "${selectedPago.value}"
                }else{
                    "Seleccione Forma de Pago"
                })
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { navHostController.navigate("login")},
               // onClick = {},
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFc33d42))
            ) {
                Text(text = "Pagar $${productPrice}", fontSize = 15.sp)
            }

            if (showBottomSheetScaffold) TutorialBottomSheetScaffold()
        }
    }
    TutorialModalBottomSheet(showModalBottomSheet, selectedPago)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialBottomSheetScaffold() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 2.dp,
        sheetContent = {
            Button(onClick = { scope.launch { scaffoldState.bottomSheetState.expand() } }) {
                Text(text = "Expand BottomSheet")
            }
            Button(onClick = { scope.launch { scaffoldState.bottomSheetState.partialExpand() } }) {
                Text(text = "PartialExpand BottomSheet")
            }
        },
        content = { Text(text = "Scaffold Content") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialModalBottomSheet(showModalBottomSheet: MutableState<Boolean>, selectedPago: MutableState<String>) {
    val scope = rememberCoroutineScope()

    if (showModalBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { showModalBottomSheet.value = false },
            sheetState = rememberModalBottomSheetState(true),
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextButton(onClick = {
                        selectedPago.value = "Efectivo"
                        showModalBottomSheet.value = false }) {
                        Text("Efectivo", fontSize = 20.sp)
                    }
                    Divider()
                    TextButton(onClick = { showModalBottomSheet.value = false }) {
                        selectedPago.value = "Tarjeta"
                        Text("Tarjeta de Debito/Credito", fontSize = 20.sp)
                    }
                   /* Divider()
                    TextButton(onClick = { showModalBottomSheet.value = false }) {
                        selectedPago.value = "Paypal"
                        Text("Paypal")
                    }*/
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        )
    }
}

/*
@Preview(showSystemUi = true)
@Composable
fun PreviewPedido() {
    DetallPedido("","","")
}*/
