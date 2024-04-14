package com.example.coleccionmania.view

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coleccionmania.data.JuegosRepository
import com.example.coleccionmania.model.JuegoResponse
import com.example.coleccionmania.model.Juegos
import com.example.coleccionmania.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class JuegosViewModel: ViewModel(){

    private val juegosRepository = JuegosRepository()
    private val allJuegos = mutableStateListOf<Juegos>()
    private val _filteredJuegos = MutableStateFlow<List<Juegos>>(emptyList())
    val filteredJuegos: StateFlow<List<Juegos>> = _filteredJuegos

    fun getJuegoById(juegoId: Int): Juegos? {
        return allJuegos.find { it.id == juegoId }
    }

    init {
        fetchJuegos()
    }

    fun fetchJuegos() {
        viewModelScope.launch {
            val response = juegosRepository.getJuegos()
            val juegosList = response.map { juegoResponse ->
                val price = Random.nextInt(20, 51).toString()
                Juegos(
                    juegoResponse.id,
                    juegoResponse.title,
                    juegoResponse.short_description,
                    price,
                    juegoResponse.thumbnail
                )
            }
            allJuegos.addAll(juegosList)
            _filteredJuegos.value = allJuegos.toList()
        }
    }

    fun filterJuegos(query: String) {
        val lowercaseQuery = query.lowercase()
        _filteredJuegos.value = allJuegos.filter { juego ->
            juego.name.lowercase().contains(lowercaseQuery) ||
                    juego.detail.lowercase().contains(lowercaseQuery)
        }
    }
}