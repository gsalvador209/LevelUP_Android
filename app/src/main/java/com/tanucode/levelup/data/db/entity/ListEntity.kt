package com.tanucode.levelup.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tanucode.levelup.util.Constants
import java.util.Date
import java.util.UUID

@Entity(tableName = Constants.DATABASE_LIST_TABLE)
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    //Para listas custom
    @ColumnInfo(name = "custom_name")
    val customName: String? = null,

    @ColumnInfo(name = "system_key")
    val systemKey: String? = null,

    val type: String, // system or custom

    @ColumnInfo(name = "color_id")
    val colorId: Int = 0,

    val icon: String? = null,

    @ColumnInfo(name="sort_order")
    val sortOrder: Int? = null,

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date()
)
