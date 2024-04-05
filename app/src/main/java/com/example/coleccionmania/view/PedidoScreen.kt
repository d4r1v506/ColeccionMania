package com.example.coleccionmania.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coleccionmania.R
import com.example.coleccionmania.navigation.TopBar
import kotlinx.coroutines.launch

@Composable
fun DetallPedido() {

    Pedido()
    TopBar("Mi Pedido")
}

@Composable
fun Pedido() {

    var showDialog by remember { mutableStateOf(false) }
    var aceptarDialog by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    var showBottomSheetScaffold by rememberSaveable { mutableStateOf(false) }
    val showModalBottomSheet = rememberSaveable { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray // Cambia aquí el color de fondo
    ) {
        // Contenido de tu pantalla aquí

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
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "logo ColeccionMania",
                    modifier = Modifier.size(200.dp)
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
                            text = "Detalle del Pedido",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold
                        )
                        Divider()
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Detalle del producto")
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
                if (aceptarDialog == true) {
                    Text(text = "Código: ${textFieldValue.text.uppercase()}", Modifier.padding(15.dp))
                }
            }

            if (showDialog) {
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

            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "A Pagar:  ", fontWeight = FontWeight.Bold, color = Color.DarkGray)
            Text(
                text = "$ 123",
                color = Color(0xFF4CAF50),
                fontSize = 25.sp,
                fontWeight = FontWeight.Black
            )


            Spacer(modifier = Modifier.height(35.dp))

            Button(
                onClick = { showModalBottomSheet.value = !showModalBottomSheet.value },
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Forma de Pago", fontSize = 15.sp)
            }
            if (showBottomSheetScaffold) TutorialBottomSheetScaffold()
        }
    }
    TutorialModalBottomSheet(showModalBottomSheet)
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
fun TutorialModalBottomSheet(showModalBottomSheet: MutableState<Boolean>) {
    val scope = rememberCoroutineScope()

    if (showModalBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { showModalBottomSheet.value = false },
            sheetState = rememberModalBottomSheetState(true),
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextButton(onClick = { showModalBottomSheet.value = false }) {
                        Text("Efectivo")
                    }
                    Divider()
                    TextButton(onClick = { showModalBottomSheet.value = false }) {
                        Text("Tarjeta de Debito/Credito")
                    }
                    Divider()
                    TextButton(onClick = { showModalBottomSheet.value = false }) {
                        Text("Paypal")
                    }


                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewPedido() {
    DetallPedido()
}