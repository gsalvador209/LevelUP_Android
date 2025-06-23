package com.tanucode.levelup.application

import android.app.Application
import com.tanucode.levelup.data.db.LevelUpDatabase
import com.tanucode.levelup.data.db.entity.ListEntity
import com.tanucode.levelup.data.db.entity.UserEntity
import com.tanucode.levelup.data.repository.ListRepository
import com.tanucode.levelup.data.repository.StatsRepositoryImpl
import com.tanucode.levelup.data.repository.TaskRepository
import com.tanucode.levelup.data.repository.UserRepository
import com.tanucode.levelup.data.repository.UserRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LevelUpApp : Application() {

    val database: LevelUpDatabase by lazy { LevelUpDatabase.getDatabase(this) }

    val taskRepository: TaskRepository by lazy { TaskRepository(database,database.taskDao(),database.taskCompletionDao()) }

    val listRepository: ListRepository by lazy { ListRepository(database.listDao()) }

    val userRepository: UserRepository by lazy { UserRepositoryImpl(database.userDao(),null) } //Actualizar para implementar remoto

    val statsRepository by lazy { StatsRepositoryImpl(taskRepository) }

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            val userDao = database.userDao()

            if(userDao.getUserById() == null){
                userDao.insertUser(
                    UserEntity(
                        name       = "User",
                        avatarUri  = null,
                        goldCoins  = 0f,
                        silverCoins= 0f
                    )
                )
            }

            if(listRepository.getAllLists().isEmpty()) {
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

                val userDao = database.userDao()
                if(userDao.getUserById() == null){
                    userDao.insertUser(
                        UserEntity(
                            name = "User",
                            avatarUri = null,
                            goldCoins = 0f,
                            silverCoins = 0f
                        )
                    )
                }

            }
        }
    }
}
