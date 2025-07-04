package com.tanucode.levelup.data.remote.datasource

import com.tanucode.levelup.data.remote.api.ProductApiService
import com.tanucode.levelup.domain.model.Product

class ProductRemoteDataSource(private val api: ProductApiService) {
    suspend fun getProducts(): List<Product> =
        api.fetchProducts().map { it.toDomain() }
}
