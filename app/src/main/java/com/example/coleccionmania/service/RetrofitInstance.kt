package com.example.coleccionmania.service

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://4392557e-d83c-4fe6-b616-9d145c552f5d.mock.pstmn.io/api/"
        //"https://4e4fc2c0-1ffe-4bab-8f91-93eff6b286e4.mock.pstmn.io/v2/"

    private val retrofit: Retrofit by lazy {
        print("La respuesta ")
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val productsService: ProductsService by lazy {
        retrofit.create(ProductsService::class.java)
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