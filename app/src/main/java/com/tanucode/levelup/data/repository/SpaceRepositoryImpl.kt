package com.tanucode.levelup.data.repository

import com.tanucode.levelup.data.local.datasource.SpaceLocalDataSource
import com.tanucode.levelup.data.remote.datasource.SpaceRemoteDataSource
import com.tanucode.levelup.domain.model.Space
import com.tanucode.levelup.domain.repository.SpaceRepository

class SpaceRepositoryImpl(
    private val local: SpaceLocalDataSource,
    private val remote: SpaceRemoteDataSource
) : SpaceRepository {

    override suspend fun getSpaces(): List<Space> {
        return try {
            val fetched = remote.getSpaces()
            local.saveSpaces(fetched)
            fetched
        } catch (e: Exception) {
            local.getSpaces()
        }
    }
}
