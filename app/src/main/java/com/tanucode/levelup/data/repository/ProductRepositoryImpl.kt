package com.tanucode.levelup.data.repository

import com.tanucode.levelup.data.local.datasource.ProductLocalDataSource
import com.tanucode.levelup.data.remote.datasource.ProductRemoteDataSource
import com.tanucode.levelup.domain.model.Product
import com.tanucode.levelup.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val local: ProductLocalDataSource,
    private val remote: ProductRemoteDataSource
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return try {
            val fetched = remote.getProducts()
            local.saveProducts(fetched)
            fetched
        } catch (e: Exception) {
            // en caso de fallo remoto devolvemos cache local
            local.getProducts()
        }
    }
}
