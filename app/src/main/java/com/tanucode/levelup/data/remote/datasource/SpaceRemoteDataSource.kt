package com.tanucode.levelup.data.remote.datasource

import com.tanucode.levelup.data.remote.api.SpaceApiService
import com.tanucode.levelup.domain.model.Space

class SpaceRemoteDataSource(private val api: SpaceApiService) {
    suspend fun getSpaces(): List<Space> =
        api.fetchSpaces().map { it.toDomain() }
}
