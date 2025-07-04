package com.tanucode.levelup.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tanucode.levelup.data.local.entity.OwnedStickerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OwnedStickerDao {

    // Productos que ya compro el user
    @Query("SELECT productId FROM owned_stickers WHERE userId = :userId")
    suspend fun getPurchasedProductIds(userId: String): List<String>

    // Misma query, pero retornando un Flow
    @Query("SELECT productId FROM owned_stickers WHERE userId = :userId")
    fun getPurchasedProductIdsFlow(userId: String): Flow<List<String>>

    // Inserta un nuevo registro de compra
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: OwnedStickerEntity)
}
