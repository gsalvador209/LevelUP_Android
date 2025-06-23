package com.tanucode.levelup.data.db.dao

import androidx.room.*
import com.tanucode.levelup.data.db.entity.UserEntity
import com.tanucode.levelup.util.Constants
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userEntity: UserEntity) //Confirm business logic

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Query("SELECT * FROM ${Constants.DATABASE_USER_TABLE} WHERE id = :id LIMIT 1")
    suspend fun getUserById(id: String = Constants.USER_ID_SINGLETON): UserEntity?

    @Query("SELECT * FROM ${Constants.DATABASE_USER_TABLE} WHERE id = :id")
    fun getUserFlow(id: String = Constants.USER_ID_SINGLETON): Flow<UserEntity>

    @Query("UPDATE ${Constants.DATABASE_USER_TABLE} SET name = :name WHERE id = :id")
    suspend fun updateName(id: String = Constants.USER_ID_SINGLETON, name: String)

    @Query("UPDATE ${Constants.DATABASE_USER_TABLE} SET avatar_uri = :uri WHERE id = :id")
    suspend fun updateAvatar(id: String = Constants.USER_ID_SINGLETON, uri: String)

    @Query("UPDATE ${Constants.DATABASE_USER_TABLE} SET gold_coins = gold_coins + :amount WHERE id = :userId")
    fun addGoldCoins(userId: UUID, amount : Float)

    @Query("UPDATE ${Constants.DATABASE_USER_TABLE} SET silver_coins = silver_coins + :amount WHERE id = :userId")
    suspend fun addSilverCoins(userId: UUID, amount: Float)

    @Update
    suspend fun updateFull(user: UserEntity)
}