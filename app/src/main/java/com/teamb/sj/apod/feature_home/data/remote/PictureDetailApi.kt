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
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String,
    ): List<PictureDetailDto>
}
