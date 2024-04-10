package com.example.coleccionmania.data

import com.example.coleccionmania.model.ProductsResponse
import com.example.coleccionmania.service.RetrofitInstance

class ProductsRepository {
    private val productsService = RetrofitInstance.productsService

    suspend fun getProducts(): ProductsResponse{
        return productsService.getProducts()
    }
}