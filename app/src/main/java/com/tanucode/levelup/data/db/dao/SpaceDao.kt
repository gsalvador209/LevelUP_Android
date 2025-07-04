package com.tanucode.levelup.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tanucode.levelup.data.local.entity.SpaceEntity

@Dao
interface SpaceDao {
    @Query("SELECT * FROM spaces")
    suspend fun getAll(): List<SpaceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SpaceEntity>)
}
