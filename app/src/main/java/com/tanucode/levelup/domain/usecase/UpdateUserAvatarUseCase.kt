package com.tanucode.levelup.domain.usecase

import com.tanucode.levelup.domain.repository.UserRepository

class UpdateUserAvatarUseCase(private val repo: UserRepository) {
    suspend operator fun invoke(uri: String) = repo.setAvatar(uri)
}
