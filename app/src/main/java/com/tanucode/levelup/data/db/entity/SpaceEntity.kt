package com.tanucode.levelup.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tanucode.levelup.domain.model.Space

@Entity(tableName = "spaces")
data class SpaceEntity(
    @PrimaryKey val id: String,
    val name: String,
    val thumbnailUrl: String
) {
    fun toDomain(): Space = Space(id, name, thumbnailUrl)
    companion object {
        fun fromDomain(s: Space) = SpaceEntity(
            id = s.id,
            name = s.name,
            thumbnailUrl = s.thumbnailUrl
        )
    }
}
