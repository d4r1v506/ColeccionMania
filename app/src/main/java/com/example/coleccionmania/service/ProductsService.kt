package com.example.coleccionmania.service

import com.example.coleccionmania.model.ProductsResponse
import retrofit2.http.GET

interface ProductsService {
    @GET("amiibo")
    suspend fun  getProducts(): ProductsResponse

}