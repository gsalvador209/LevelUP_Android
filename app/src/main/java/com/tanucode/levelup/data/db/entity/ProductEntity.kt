package com.tanucode.levelup.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tanucode.levelup.domain.model.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val title: String,
    val category: String,
    val price: Double,
    val imageUrl: String
) {
    fun toDomain(): Product = Product(id, title, category, price, imageUrl)
    companion object {
        fun fromDomain(p: Product) = ProductEntity(
            id = p.id,
            title = p.title,
            category = p.category,
            price = p.price,
            imageUrl = p.imageUrl
        )
    }
}
