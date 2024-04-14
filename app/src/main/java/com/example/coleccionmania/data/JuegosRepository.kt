package com.example.coleccionmania.data

import com.example.coleccionmania.model.JuegoResponse
import com.example.coleccionmania.model.JuegosResponse
import com.example.coleccionmania.model.ProductsResponse
import com.example.coleccionmania.service.RetrofitInstance

class JuegosRepository {
    private val juegosService = RetrofitInstance.juegosService

    suspend fun getJuegos(): List<JuegoResponse> {
        return juegosService.getJuegos()
    }
}