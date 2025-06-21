package com.tanucode.levelup.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithCompletionsEntity(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "task_id"
    )
    val completions: List<TaskCompletionEntity>
)