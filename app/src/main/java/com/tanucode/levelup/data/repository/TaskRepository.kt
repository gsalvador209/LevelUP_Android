package com.tanucode.levelup.data.repository

import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.tanucode.levelup.data.db.LevelUpDatabase
import com.tanucode.levelup.data.db.dao.TaskCompletionDao
import com.tanucode.levelup.data.db.dao.TaskDao
import com.tanucode.levelup.data.db.entity.TaskCompletionEntity
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.db.entity.TaskWithCompletionsEntity
import com.tanucode.levelup.data.db.entity.TaskWithListEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date
import java.util.UUID

class TaskRepository (
    private val db: LevelUpDatabase,
    private val taskDao: TaskDao = db.taskDao(),
    private val completionDao: TaskCompletionDao = db.taskCompletionDao()
){

    val allTasks : LiveData<List<TaskEntity>> = taskDao.getAllTasks()

    suspend fun insertTask(task : TaskEntity){
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }


    suspend fun getTaskById(taskId: UUID): TaskEntity? {
        return taskDao.getTaskById(taskId)
    }

    suspend fun getTasksByList(listId: UUID): List<TaskEntity> {
        return taskDao.getTaskByList(listId)
    }

    suspend fun getAllPendingTasks(): List<TaskEntity> {
        return taskDao.getAllPendingTasks()
    }

    suspend fun getCompletionsByList(listId : Long): List<TaskWithCompletionsEntity> {
        return taskDao.getTasksWithCompletitions(listId)
    }

    fun getAllTasksWithList(): LiveData<List<TaskWithListEntity>> =
        taskDao.getAllTasksWithList()

    fun getScheduledTasks() : LiveData<List<TaskEntity>> =
        taskDao.getScheduledTask()

    //Para taskComppletion
    suspend fun toggleTaskCompletion(task: TaskEntity) {
        db.withTransaction {
            val now = Date()

            if(!task.isCompleted){
                val updated = task.copy(isCompleted = true, completedAt = now)
                taskDao.updateTask(updated)

                completionDao.insertCompletion(
                    TaskCompletionEntity(taskId = task.id, completedAt = now)
                )
            }else{
                completionDao.deleteLastCompletion(task.id)
                val updated = task.copy(isCompleted = false, completedAt = null)
                taskDao.updateTask(updated)
            }
        }
    }

}