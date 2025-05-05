package com.tanucode.levelup.data.db.dao

import androidx.room.*
import com.tanucode.levelup.data.db.entity.TaskEntity
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

}