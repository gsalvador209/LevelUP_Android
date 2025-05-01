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

    val name: String,

    val type: String, // system or custom

    val color: String? = null,

    val icon: String? = null,

    @ColumnInfo(name="sort_order")
    val sortOrder: Int? = null,

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date()
)
