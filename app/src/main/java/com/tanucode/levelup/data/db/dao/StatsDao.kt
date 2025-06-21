package com.tanucode.levelup.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.tanucode.levelup.data.db.entity.ListWithTasksAndCompletions
import com.tanucode.levelup.util.Constants

@Dao
interface StatsDao {
    @Transaction
    @Query("SELECT * FROM ${Constants.DATABASE_LIST_TABLE}")
    fun getAllListsWithCompletions(): LiveData<List<ListWithTasksAndCompletions>>
}