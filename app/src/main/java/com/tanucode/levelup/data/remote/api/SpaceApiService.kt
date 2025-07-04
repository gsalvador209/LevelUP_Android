package com.tanucode.levelup.data.remote.api

import com.tanucode.levelup.data.remote.model.SpaceResponse
import retrofit2.http.GET

interface SpaceApiService {
    @GET("spaces")
    suspend fun fetchSpaces(): List<SpaceResponse>
}
