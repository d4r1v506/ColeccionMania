package com.example.coleccionmania

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coleccionmania.data.ProductsRepository
import com.example.coleccionmania.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random


class ProductsViewModel: ViewModel() {
    private val productsRepository = ProductsRepository()
    private val allProducts = mutableStateListOf<Product>()
    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts

    fun getProductById(productId: String): Product? {
        return allProducts.find { it.id == productId } // Suponiendo que Product tiene una propiedad "id" que es String
    }
    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            val response = productsRepository.getProducts().amiibo
            val productsResponse = response.map { productResponse ->
                val price = Random.nextInt(20, 51).toString()
                Product(
                    productResponse.head,
                    productResponse.character,
                    productResponse.amiiboSeries,
                    price,
                    productResponse.image
                )
            }
            allProducts.addAll(productsResponse)
            _filteredProducts.value = allProducts.toList() // Al inicio, los productos filtrados serán todos los productos disponibles
        }
    }

    fun filterProducts(query: String) {
        val lowercaseQuery = query.lowercase()
        _filteredProducts.value = allProducts.filter { product ->
            product.name.lowercase().contains(lowercaseQuery) ||
                    product.detail.lowercase().contains(lowercaseQuery)
        }
    }
}
