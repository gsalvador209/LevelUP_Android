package com.tanucode.levelup.domain.usecase

import com.tanucode.levelup.domain.repository.UserRepository

class UpdateUserSilverCoinsUseCase(
    private val repo: UserRepository

) {
    suspend operator fun invoke(userId: String, newBalance: Float) {
        repo.getUser(userId)?.let { user ->
            repo.updateUser(user.copy(silverCoins = newBalance))
        }
    }
}
