package com.tanucode.levelup.data.db.converter

import androidx.room.TypeConverter
import java.util.UUID

class UUIDTypeConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID?) : String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return uuid?.let { UUID.fromString(it)}
    }
}