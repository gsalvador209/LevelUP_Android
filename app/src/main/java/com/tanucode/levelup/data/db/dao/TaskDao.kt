package com.tanucode.levelup.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.db.entity.TaskWithListEntity
import com.tanucode.levelup.util.Constants
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM task WHERE id = :taskId")
    suspend fun getTaskById(taskId: UUID): TaskEntity?

    @Query("SELECT * FROM task WHERE list_id = :listId ORDER BY start_date ASC")
    suspend fun getTaskByList(listId: UUID): List<TaskEntity>

    @Query("SELECT * FROM task WHERE is_completed = 0 ORDER BY start_date ASC")
    suspend fun getAllPendingTasks(): List<TaskEntity>

    @Query("SELECT * FROM task ORDER BY created_at DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    // Devuelve todas las tareas junto con su lista padre
    @Transaction
    @Query("SELECT * FROM ${Constants.DATABASE_TASK_TABLE} ORDER BY created_at DESC")
    fun getAllTasksWithList(): LiveData<List<TaskWithListEntity>>

}