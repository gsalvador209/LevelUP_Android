package com.tanucode.levelup.application

import android.app.Application
import com.tanucode.levelup.data.db.LevelUpDatabase
import com.tanucode.levelup.data.db.entity.ListEntity
import com.tanucode.levelup.data.repository.ListRepository
import com.tanucode.levelup.data.repository.TaskRepository
import com.tanucode.levelup.data.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LevelUpApp : Application() {

    val database: LevelUpDatabase by lazy { LevelUpDatabase.getDatabase(this) }

    val taskRepository: TaskRepository by lazy { TaskRepository(database.taskDao()) }

    val listRepository: ListRepository by lazy { ListRepository(database.listDao()) }

    val userRepository: UserRepository by lazy { UserRepository(database.userDao()) }

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            if(listRepository.getAllLists().isEmpty()) {
                listRepository.insertList(
                    ListEntity(
                        customName = null,
                        systemKey = "inbox",
                        type = "system",
                        colorId = 9,
                        icon = "ic_inbox",
                        sortOrder = 0
                    )
                )

                listRepository.insertList(
                    ListEntity(
                        customName = null,
                        systemKey = "objectives",
                        type = "system",
                        colorId = 2,
                        icon = "ic_inbox",
                        sortOrder = 0
                    )
                )
            }
        }
    }

}
