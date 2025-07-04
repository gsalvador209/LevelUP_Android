package com.tanucode.levelup.data.repository

import com.tanucode.levelup.data.db.dao.UserDao
import com.tanucode.levelup.data.db.entity.UserEntity
import com.tanucode.levelup.data.local.datasource.UserLocalDataSource
import com.tanucode.levelup.data.remote.UserRemoteDataSource
import com.tanucode.levelup.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val local: UserLocalDataSource,
    private val remote: UserRemoteDataSource? = null
) : UserRepository{

    override fun userFlow() : Flow<UserEntity> =
        local.userFlow()

    override suspend fun setName(name: String) {
        local.setName(name = name)
        //Sincronización con remoto
        //userFlow().take(1).collect { remote?.syncUser(it)}
    }

    override suspend fun setAvatar(uri: String) {
        local.setAvatar(uri = uri)
        //userFlow().take(1).collect { remote?.syncUser(it)}
    }

    override suspend fun getUser(userId: String): UserEntity? =
        local.getUser(userId)

    override suspend fun updateUser(user: UserEntity) =
        local.updateUser(user)
}