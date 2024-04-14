package com.example.coleccionmania.model

import com.google.gson.annotations.SerializedName

data class JuegosResponse (
    @SerializedName("juegos")
    val juego: List<JuegoResponse>
)