package com.example.coleccionmania.service

import com.example.coleccionmania.model.JuegoResponse
import com.example.coleccionmania.model.Juegos
import com.example.coleccionmania.model.JuegosResponse
import retrofit2.http.GET

interface JuegosService {

    @GET("games?category=moba")
    suspend fun getJuegos(): List<JuegoResponse>
}