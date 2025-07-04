package com.tanucode.levelup.data.local.datasource

import com.tanucode.levelup.data.local.dao.SpaceDao
import com.tanucode.levelup.data.local.entity.SpaceEntity
import com.tanucode.levelup.domain.model.Space

class SpaceLocalDataSource(private val dao: SpaceDao) {
    suspend fun getSpaces(): List<Space> =
        dao.getAll().map { it.toDomain() }

    suspend fun saveSpaces(list: List<Space>) =
        dao.insertAll(list.map { SpaceEntity.fromDomain(it) })
}
