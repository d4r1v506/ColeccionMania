package com.example.coleccionmania.service

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL ="https://www.amiiboapi.com/api/"// "https://4392557e-d83c-4fe6-b616-9d145c552f5d.mock.pstmn.io/api/"
    private const val BASE_URL_GAME = "https://www.mmobomb.com/api1/"//"https://1765cc62-eb8f-4d51-a74d-7534088905b3.mock.pstmn.io/api1/"

    private val retrofit: Retrofit by lazy {
        print("La respuesta ")
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private val retrofitGame: Retrofit by lazy {
        print("La respuesta ")
        Retrofit.Builder()
            .baseUrl(BASE_URL_GAME)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val productsService: ProductsService by lazy {
        retrofit.create(ProductsService::class.java)
    }

    val juegosService: JuegosService by lazy {
        retrofitGame.create(JuegosService::class.java)
    }



    class ResponseInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val response = chain.proceed(request)

            // Si la respuesta es un código 200, imprime en la consola
            if (response.code == 200) {
                println("La respuesta fue exitosa: ${response.code}")
            }

            return response
        }
    }
}