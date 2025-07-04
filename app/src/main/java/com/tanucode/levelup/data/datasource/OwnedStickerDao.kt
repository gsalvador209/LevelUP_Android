package com.tanucode.levelup.data.local.datasource

import com.tanucode.levelup.data.local.dao.OwnedStickerDao
import com.tanucode.levelup.data.local.entity.OwnedStickerEntity
import com.tanucode.levelup.util.Constants
import kotlinx.coroutines.flow.Flow

class OwnedStickerLocalDataSource(
    private val dao: OwnedStickerDao
) {
    suspend fun getPurchasedIds(userId: String): List<String> =
        dao.getPurchasedProductIds(userId)

    fun getPurchasedProductIdsFlow() : Flow<List<String>> =
        dao.getPurchasedProductIdsFlow(Constants.USER_ID_SINGLETON)

    suspend fun savePurchase(userId: String, productId: String) {
        val now = System.currentTimeMillis()
        dao.insert(OwnedStickerEntity(
            userId   = userId,
            productId= productId,
            purchasedAt = now
        ))
    }
}
