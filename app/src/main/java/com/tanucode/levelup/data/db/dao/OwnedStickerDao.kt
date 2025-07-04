package com.tanucode.levelup.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tanucode.levelup.data.local.entity.OwnedStickerEntity

@Dao
interface OwnedStickerDao {

    /** Devuelve todos los productId que el user ya compró */
    @Query("SELECT productId FROM owned_stickers WHERE userId = :userId")
    suspend fun getPurchasedProductIds(userId: String): List<String>

    /** Inserta un nuevo registro de compra */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: OwnedStickerEntity)
}
