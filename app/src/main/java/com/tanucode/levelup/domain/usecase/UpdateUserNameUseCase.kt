package com.tanucode.levelup.domain.usecase

import com.tanucode.levelup.domain.repository.UserRepository

class UpdateUserNameUseCase(private val repo: UserRepository) {
    suspend operator fun invoke(newName : String) = repo.setName(newName)
}