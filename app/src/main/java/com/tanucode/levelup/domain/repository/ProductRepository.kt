package com.tanucode.levelup.domain.repository

import com.tanucode.levelup.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}
