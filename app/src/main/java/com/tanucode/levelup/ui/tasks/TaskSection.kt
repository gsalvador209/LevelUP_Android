package com.tanucode.levelup.ui.tasks

import com.tanucode.levelup.data.db.entity.TaskWithListEntity

sealed class TaskSection {
    data class Header(val title: String): TaskSection()
    data class Item(val data: TaskWithListEntity): TaskSection()
}