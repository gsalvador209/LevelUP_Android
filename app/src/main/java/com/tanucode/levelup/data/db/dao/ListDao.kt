package com.tanucode.levelup.data.db.dao

import androidx.room.*
import com.tanucode.levelup.data.db.entity.ListEntity
import java.util.UUID

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(listEntity: ListEntity)

    @Update
    suspend fun updateList(listEntity: ListEntity)

    @Delete //Update Taks with this id to Inbox
    suspend fun deleteList(listEntity: ListEntity)

    @Query("SELECT * FROM list WHERE id = :listId")
    suspend fun getListById(listId: UUID) : ListEntity?

    @Query("SELECT * FROM list ORDER BY sort_order ASC, created_at ASC")
    suspend fun getAllLists(): List<ListEntity>
}