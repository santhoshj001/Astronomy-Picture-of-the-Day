package com.teamb.sj.apod.feature_home.data.remote

import com.teamb.sj.apod.feature_home.data.remote.dto.PictureDetailDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureDetailApi {
    companion object {
        const val BASE_URL = "https://api.nasa.gov/"
        const val path = "planetary/apod"
    }

    @GET("$path?thumbs=true")
    suspend fun getPicture(
        @Query("date") date: String,
        @Query("api_key") apiKey: String,
    ): PictureDetailDto
}
