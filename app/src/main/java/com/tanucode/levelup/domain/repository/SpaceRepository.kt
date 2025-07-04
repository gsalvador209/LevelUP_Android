package com.tanucode.levelup.domain.repository

import com.tanucode.levelup.domain.model.Space

interface SpaceRepository {
    suspend fun getSpaces(): List<Space>
}
