package com.tanucode.levelup.data.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    // TODO: cambia esta URL por la de tu backend (terminada en ‘/’)
    private const val BASE_URL = "https://tu-backend.com/api/"

    // Configuramos un interceptor de logs (opcional, útil en debug)
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Cliente HTTP
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    // Instancia singleton de Retrofit
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Servicios expuestos
    val productApi: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }

    val spaceApi: SpaceApiService by lazy {
        retrofit.create(SpaceApiService::class.java)
    }
}
