package com.tanucode.levelup.data.repository

import com.tanucode.levelup.data.db.dao.UserDao
import com.tanucode.levelup.data.db.entity.UserEntity
import com.tanucode.levelup.data.remote.UserRemoteDataSource
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val dao: UserDao,
    private val remote: UserRemoteDataSource? = null
) : UserRepository{

    override fun userFlow() : Flow<UserEntity> =
        dao.getUserFlow()

    override suspend fun setName(name: String) {
        dao.updateName(name = name)
        //Sincronización con remoto
        //userFlow().take(1).collect { remote?.syncUser(it)}
    }

    override suspend fun setAvatar(uri: String) {
        dao.updateAvatar(uri = uri)
        //userFlow().take(1).collect { remote?.syncUser(it)}
    }
}