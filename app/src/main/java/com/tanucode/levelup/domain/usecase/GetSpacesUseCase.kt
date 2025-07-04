package com.tanucode.levelup.domain.usecase

import com.tanucode.levelup.domain.model.Space
import com.tanucode.levelup.domain.repository.SpaceRepository

class GetSpacesUseCase(
    private val repo: SpaceRepository
) {
    suspend operator fun invoke(): List<Space> =
        repo.getSpaces()
}
