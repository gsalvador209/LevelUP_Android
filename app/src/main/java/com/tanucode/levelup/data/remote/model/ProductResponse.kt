package com.tanucode.levelup.data.remote.model

import com.google.gson.annotations.SerializedName
import com.tanucode.levelup.domain.model.Product

data class ProductResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("category") val category: String,
    @SerializedName("price") val price: Double,
    @SerializedName("imageUrl") val imageUrl: String
) {
    fun toDomain() = Product(id, title, category, price, imageUrl)
}
