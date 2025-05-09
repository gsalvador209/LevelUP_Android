package com.tanucode.levelup.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation


//Combinación entre la lista y las tareas
data class TaskWithListEntity(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "list_id",
        entityColumn = "id"
    )
    val list: ListEntity
)
