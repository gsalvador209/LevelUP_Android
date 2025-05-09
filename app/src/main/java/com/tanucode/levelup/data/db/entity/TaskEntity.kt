package com.tanucode.levelup.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tanucode.levelup.util.Constants
import java.util.Date
import java.util.UUID


@Entity(tableName = Constants.DATABASE_TASK_TABLE)
data class TaskEntity(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    val title : String,
    val description: String? = null,

    @ColumnInfo(name = "list_id")
    val listId : Long,

    @ColumnInfo(name = "start_date")
    val startDate: Date? = null,
    @ColumnInfo(name = "end_date")
    val endDate: Date? = null,

    val deadline: Long? = null,

    @ColumnInfo(name = "estimated_duration")
    val estimatedDuration : Int? = null,

    @ColumnInfo(name = "is_completed")
    val isCompleted : Boolean = false,
    @ColumnInfo(name = "completed_at")
    val completedAt : Date? = null,
    @ColumnInfo(name = "was_cancelled")
    val wasCancelled : Boolean = false,

    @ColumnInfo(name = "was_focused")
    val wasFocused: Boolean = false,

    @ColumnInfo(name = "repetition_config")
    val repetitionConfig: String? = null, // JSON stored as String
    @ColumnInfo(name = "reminders")
    val reminders: String? = null, // JSON stored as String

    @ColumnInfo(name = "earned_coins")
    val earnedCoins: Float = 0f,
    @ColumnInfo(name = "reward_type")
    val rewardType: String? = null, // "gold", "silver", "none"

    @ColumnInfo(name = "external_calendar_id")
    val externalCalendarId: String? = null,

    @ColumnInfo(name = "is_synced_with_calendar")
    val isSyncedWithCalendar: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),
    @ColumnInfo(name = "updated_at")
    val updatedAt: Date = Date()
)