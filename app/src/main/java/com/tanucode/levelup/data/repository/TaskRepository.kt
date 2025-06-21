package com.tanucode.levelup.data.repository

import androidx.lifecycle.LiveData
import com.tanucode.levelup.data.db.dao.TaskDao
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.db.entity.TaskWithCompletionsEntity
import com.tanucode.levelup.data.db.entity.TaskWithListEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class TaskRepository (
    private val taskDao: TaskDao
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



}