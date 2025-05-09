package com.tanucode.levelup.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tanucode.levelup.data.db.entity.ListEntity
import com.tanucode.levelup.util.Constants
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

    @Query("SELECT * FROM ${Constants.DATABASE_LIST_TABLE} ORDER BY sort_order")
    fun getAllListsLive(): LiveData<List<ListEntity>>
}