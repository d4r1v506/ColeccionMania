package com.example.coleccionmania.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.coleccionmania.R
import com.example.coleccionmania.navigation.TopBar

@Composable
fun ContenidoNewProudct(productImage: String,navHostController: NavHostController){
    var titulo = remember { mutableStateOf("") }
    var descripcion = remember { mutableStateOf("") }
    var precio = remember { mutableStateOf("") }
    TopBar("Nuevo",navHostController)
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(vertical = 60.dp)
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(productImage.isNotEmpty()){
            Image(
                painter = rememberImagePainter(productImage),
                contentDescription = "logo ColeccionMania",
                modifier = Modifier.size(160.dp)
            )
        }else{
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "logo ColeccionMania",
                modifier = Modifier.size(160.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Ingrese un titulo")
        TextField(
            //value = "",
            //onValueChange = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = titulo.value, onValueChange = { newTitulo -> titulo.value = newTitulo },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(10.dp)
        )
        Text(text = "Ingrese una descripciòn")
        TextField(
           // value = "",
           // onValueChange = { /*TODO*/ },

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = descripcion.value, onValueChange = { newDescripcion -> descripcion.value = newDescripcion },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(10.dp)
        )
        Text(text = "Ingrese un valor")
        TextField(
           // value = "",
           // onValueChange = { /*TODO*/ },
            modifier = Modifier
                .width(200.dp)
                .padding(horizontal = 16.dp),
            value = precio.value, onValueChange = { newPrecio -> precio.value = newPrecio },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(26.dp))
        Button(
            onClick = { },
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFc33d42))
        ) {
            Text(text = "Añadir Producto", fontSize = 15.sp)
        }

        }
        IconButton(
            onClick = { navHostController.navigate("camera") },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_photo_camera_24), // Aquí debes reemplazar ic_camera con el nombre de tu icono de cámara
                contentDescription = "Botón de cámara"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewContenido(){
    val navController = rememberNavController()
    ContenidoNewProudct("", navController)
}
