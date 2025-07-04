package com.tanucode.levelup.data.repository

import android.util.Log
import com.tanucode.levelup.data.local.datasource.ProductLocalDataSource
import com.tanucode.levelup.data.remote.datasource.ProductRemoteDataSource
import com.tanucode.levelup.domain.model.Product
import com.tanucode.levelup.domain.repository.ProductRepository
import com.tanucode.levelup.util.Constants

class ProductRepositoryImpl(
    private val local: ProductLocalDataSource,
    private val remote: ProductRemoteDataSource
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return try {
            val fetched = remote.getProducts()
            local.saveProducts(fetched)
            Log.d(Constants.LOGS_MESSAGE, "Downloaded ${fetched.size} items")
            fetched
        } catch (e: Exception) {
            // en caso de fallo remoto devolvemos cache local
            Log.d(Constants.LOGS_MESSAGE, "Error: ${e.message}")
            local.getProducts()
        }
    }
}
