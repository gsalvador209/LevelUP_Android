package com.tanucode.levelup.data.db.dao

import androidx.room.*
import com.tanucode.levelup.data.db.entity.UserEntity
import java.util.UUID

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity) //Confirm business logic

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Query("UPDATE user SET gold_coins = gold_coins + :amount WHERE id = :userId")
    fun addGoldCoins(userId: UUID, amount : Float)

    @Query("UPDATE user SET silver_coins = silver_coins + :amount WHERE id = :userId")
    suspend fun addSilverCoins(userId: UUID, amount: Float)

}