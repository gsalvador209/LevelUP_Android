package com.tanucode.levelup.domain.repository

import kotlinx.coroutines.flow.Flow

interface OwnedStickerRepository {
    suspend fun getPurchasedProductIds(userId: String): List<String>
    fun getPurchasedIdsFlow(): Flow<List<String>>
    suspend fun purchaseProduct(userId: String, productId: String)
}