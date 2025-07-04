package com.tanucode.levelup.data.remote.model

import com.google.gson.annotations.SerializedName
import com.tanucode.levelup.domain.model.Space

data class SpaceResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
) {
    fun toDomain() = Space(id, name, thumbnailUrl)
}
