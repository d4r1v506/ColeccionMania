package com.example.coleccionmania.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coleccionmania.R


@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(Modifier.align(Alignment.Center))
    }
}
@Composable
fun Login(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        HeaderImage()
        Form()
        OlvidePassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.height(60.dp))
        Botones()
        Version()
    }
}

@Composable
fun HeaderImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = "logo cabecera"
    )
}
@Composable
fun Form() {
    var usuario = remember { mutableStateOf("")}
    var password = remember { mutableStateOf("")}

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp)
    ) {
        Text(text = "Usuario")
    }
    TextField(
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Ingrese usuario",
                tint = Color.LightGray,
                modifier = Modifier
                    .size(20.dp)
            )
        },

        value = usuario.value, onValueChange = {newUsuario -> usuario.value = newUsuario},

        keyboardOptions = KeyboardOptions.Default,
        //keyboardActions = KeyboardActions.Default,
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(10.dp)
    )
    Spacer(modifier = Modifier.height(30.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp)
    ) {
        Text(text = "Contraseña")

    }
    TextField(
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier
                    .padding(8.dp)
                    .size(20.dp)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Ingrese password",
                tint = Color.LightGray,
                modifier = Modifier
                    .size(20.dp)
            )
        },
        value = password.value, onValueChange = {newPassword -> password.value = newPassword},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
fun OlvidePassword(modifier: Modifier) {
    Text(
        text = "Olvide la contraseña?",
        modifier
            .clickable { }
            .padding(end = 40.dp),
        fontWeight = FontWeight.Bold,
        color = Color(0xFF3F51B5)
    )
}
@Composable
fun Botones() {
    Button(
        onClick = { /*TODO*/ },
        Modifier.width(150.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = "Iniciar Sesión", fontSize = 15.sp)
    }

    OutlinedButton(
        onClick = { /*TODO*/ },
        Modifier.width(150.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = "Registrarse", fontSize = 15.sp)
    }
}

@Composable
fun Version() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Text(text = "V 1.0.0", Modifier.padding(end = 16.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewForm() {
    LoginScreen()
}