package com.tanucode.levelup.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tanucode.levelup.util.Constants
import java.util.Date
import java.util.UUID

@Entity(tableName = Constants.DATABASE_USER_TABLE)
data class UserEntity(
    @PrimaryKey
    val id : String = Constants.USER_ID_SINGLETON,

    val name: String,

    @ColumnInfo(name = "avatar_uri")
    val avatarUri: String? = null,

    @ColumnInfo(name = "gold_coins")
    val goldCoins: Float = 0f,

    @ColumnInfo(name = "silver_coins")
    val silverCoins: Float = 0f,

    @ColumnInfo(name = "registration_date")
    val registrationDate: Date = Date()
)

