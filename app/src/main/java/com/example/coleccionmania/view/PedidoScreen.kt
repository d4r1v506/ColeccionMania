package com.example.coleccionmania.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coleccionmania.R

@Composable
fun Pedido() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
            Text(text = "Mi Pedido", color = Color(0xFF2196F3))

        }
        Box(modifier = Modifier.padding(horizontal = 8.dp)) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "logo ColeccionMania"
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
            Column {
                Text(text = "Descripción")
                Text(text = "Detalle del producto")
            }

        }


        Text(text = "Precio")
        Text(text = "$ 123", color = Color.Green, fontSize = 20.sp)
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
            Text(text = "Forma de pago")
        }
//        Spacer(modifier = Modifier.weight(10.dp))
        Button(
            onClick = { /*TODO*/ },
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Efectivo", fontSize = 15.sp)
        }
        Button(
            onClick = { /*TODO*/ },
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Tarjeta Debito/Credito", fontSize = 15.sp)
        }
        Button(
            onClick = { /*TODO*/ },
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Paypal", fontSize = 15.sp)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewPedido() {
    Pedido()
}