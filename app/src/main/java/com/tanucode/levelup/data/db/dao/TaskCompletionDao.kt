package com.tanucode.levelup.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tanucode.levelup.data.db.entity.TaskCompletionEntity
import com.tanucode.levelup.util.Constants

@Dao
interface TaskCompletionDao {
    @Insert
    suspend fun insertCompletion(completion: TaskCompletionEntity)

    @Query("""
            DELETE FROM ${Constants.DATABASE_COMPLETIONS_TABLE}
            WHERE id = (
                SELECT id
                FROM ${Constants.DATABASE_COMPLETIONS_TABLE}
                WHERE task_id = :taskId
                ORDER BY completed_at DESC
                LIMIT 1
            )
            """)
    suspend fun deleteLastCompletion(taskId : Long)
}