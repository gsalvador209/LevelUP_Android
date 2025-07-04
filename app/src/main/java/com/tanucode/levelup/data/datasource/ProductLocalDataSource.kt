package com.tanucode.levelup.data.local.datasource

import com.tanucode.levelup.data.local.dao.ProductDao
import com.tanucode.levelup.data.local.entity.ProductEntity
import com.tanucode.levelup.domain.model.Product

class ProductLocalDataSource(private val dao: ProductDao) {
    suspend fun getProducts(): List<Product> =
        dao.getAll().map { it.toDomain() }

    suspend fun saveProducts(list: List<Product>) =
        dao.insertAll(list.map { ProductEntity.fromDomain(it) })
}
