package com.tanucode.levelup.application

import android.app.Application
import com.tanucode.levelup.data.db.LevelUpDatabase
import com.tanucode.levelup.data.repository.ListRepository
import com.tanucode.levelup.data.repository.TaskRepository
import com.tanucode.levelup.data.repository.UserRepository

class LevelUpApp : Application() {

    val database: LevelUpDatabase by lazy {
        LevelUpDatabase.getDatabase(this)
    }

    val taskRepository: TaskRepository by lazy {
        TaskRepository(database.taskDao())
    }

    val listRepository: ListRepository by lazy {
        ListRepository(database.listDao())
    }

    val userRepository: UserRepository by lazy {
        UserRepository(database.userDao())
    }
}
