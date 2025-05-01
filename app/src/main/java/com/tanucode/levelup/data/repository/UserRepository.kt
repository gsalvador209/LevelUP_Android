package com.tanucode.levelup.data.repository
import com.tanucode.levelup.data.db.dao.UserDao
import com.tanucode.levelup.data.db.entity.UserEntity
import java.util.UUID

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }

    suspend fun updateUser(userEntity: UserEntity) {
        userDao.updateUser(userEntity)
    }

    suspend fun getUser(): UserEntity? {
        return userDao.getUser()
    }

    suspend fun addGoldCoins(userId: UUID, amount: Float) {
        userDao.addGoldCoins(userId, amount)
    }

    suspend fun addSilverCoins(userId: UUID, amount: Float) {
        userDao.addSilverCoins(userId, amount)
    }
}
