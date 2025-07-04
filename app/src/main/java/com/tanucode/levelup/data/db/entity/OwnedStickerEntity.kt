package com.tanucode.levelup.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tanucode.levelup.util.Constants

/**
 * Si tu app es single-user puedes omitir userId.
 * Si hay múltiples usuarios, úsalo para distinguir compras.
 */
@Entity(tableName = "owned_stickers")
data class OwnedStickerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: String = Constants.USER_ID_SINGLETON,        // e.g. “current_user_id”
    val productId: String,     // FK a ProductEntity.id
    val purchasedAt: Long      // timestamp en millis
)
