package com.tanucode.levelup.data.repository

import com.tanucode.levelup.data.local.datasource.OwnedStickerLocalDataSource
import com.tanucode.levelup.domain.repository.OwnedStickerRepository
import kotlinx.coroutines.flow.Flow

class OwnedStickerRepositoryImpl(
    private val local: OwnedStickerLocalDataSource
) : OwnedStickerRepository {

    override suspend fun getPurchasedProductIds(userId: String): List<String> =
        local.getPurchasedIds(userId)

    override fun getPurchasedIdsFlow(): Flow<List<String>> =
        local.getPurchasedProductIdsFlow()


    override suspend fun purchaseProduct(userId: String, productId: String) {
        local.savePurchase(userId, productId)
    }

}
