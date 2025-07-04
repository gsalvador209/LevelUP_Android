package com.tanucode.levelup.data.local.datasource

import com.tanucode.levelup.data.db.dao.UserDao
import com.tanucode.levelup.data.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow


class UserLocalDataSource(private val dao: UserDao) {

    fun userFlow() : Flow<UserEntity> =
        dao.getUserFlow()

    suspend fun setName(name: String) {
        dao.updateName(name = name)
        //Sincronización con remoto
        //userFlow().take(1).collect { remote?.syncUser(it)}
    }

    suspend fun setAvatar(uri: String) {
        dao.updateAvatar(uri = uri)
        //userFlow().take(1).collect { remote?.syncUser(it)}
    }

    suspend fun getUser(userId: String): UserEntity? =
        dao.getUserById(userId)

    suspend fun updateUser(user: UserEntity) =
        dao.updateFull(user)
}
