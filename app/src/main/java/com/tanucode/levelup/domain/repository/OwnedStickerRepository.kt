package com.tanucode.levelup.domain.repository

interface OwnedStickerRepository {
    suspend fun getPurchasedProductIds(userId: String): List<String>
    suspend fun purchaseProduct(userId: String, productId: String)
}