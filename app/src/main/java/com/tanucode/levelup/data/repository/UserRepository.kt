package com.tanucode.levelup.data.repository
import com.tanucode.levelup.data.db.dao.UserDao
import com.tanucode.levelup.data.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface UserRepository {

    fun userFlow(): Flow<UserEntity>

    suspend fun setName(name: String)

    suspend fun setAvatar(uri: String)
}
