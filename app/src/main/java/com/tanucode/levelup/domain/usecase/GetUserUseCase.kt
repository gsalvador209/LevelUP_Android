package com.tanucode.levelup.domain.usecase

import com.tanucode.levelup.data.db.entity.UserEntity
import com.tanucode.levelup.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase (private val repo: UserRepository){
    operator fun invoke(): Flow<UserEntity> = repo.userFlow()
}