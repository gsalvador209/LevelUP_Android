package com.tanucode.levelup.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ListWithTasksAndCompletions(
    @Embedded val list: ListEntity,
    @Relation(
        entity = TaskEntity::class,
        parentColumn = "id",
        entityColumn = "list_id"
    )
    val tasks: List<TaskWithCompletionsEntity>
)