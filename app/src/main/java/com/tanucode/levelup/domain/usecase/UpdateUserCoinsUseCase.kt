package com.tanucode.levelup.domain.usecase

import com.tanucode.levelup.domain.repository.UserRepository

class UpdateUserCoinsUseCase(
    private val repo: UserRepository

) {
    suspend operator fun invoke(userId: String, newBalance: Float) {
        repo.getUser(userId)?.let { user ->
            repo.updateUser(user.copy(goldCoins = newBalance))
        }
    }
}
