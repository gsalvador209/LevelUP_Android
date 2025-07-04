package com.tanucode.levelup.domain.repository

import com.tanucode.levelup.data.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun userFlow() : Flow<UserEntity>
    suspend fun getUser(userId: String): UserEntity?
    suspend fun updateUser(user: UserEntity)
    suspend fun setName(name : String)
    suspend fun setAvatar(uri: String)
}