package com.tanucode.levelup.data.remote.api

import com.tanucode.levelup.data.remote.model.ProductResponse
import retrofit2.http.GET

interface ProductApiService {
    @GET("stickers/all_stickers")
    suspend fun fetchProducts(): List<ProductResponse>
}
