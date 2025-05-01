package com.tanucode.levelup.data.db.converter

import androidx.room.TypeConverter
import java.util.Date
import java.util.UUID

class DateTypeConverter {

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }
}