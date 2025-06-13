package com.tanucode.levelup.data.db.entity

import androidx.room.PrimaryKey

data class RecurrenceRuleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val taskId : Long,
    val rrule: String,
    val dtstartUtc : Long
)