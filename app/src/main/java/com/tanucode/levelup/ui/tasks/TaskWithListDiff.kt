package com.tanucode.levelup.ui.tasks

import androidx.recyclerview.widget.DiffUtil
import com.tanucode.levelup.data.db.entity.TaskWithListEntity

object TaskWithListDiff : DiffUtil.ItemCallback<TaskWithListEntity>() {
    override fun areItemsTheSame(
        oldItem: TaskWithListEntity,
        newItem: TaskWithListEntity
    ): Boolean = oldItem.task.id == newItem.task.id

    override fun areContentsTheSame(
        oldItem: TaskWithListEntity,
        newItem: TaskWithListEntity
    ): Boolean = oldItem == newItem
}