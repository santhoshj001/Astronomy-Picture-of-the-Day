package com.teamb.sj.apod.feature_home.data.remote

import com.teamb.sj.apod.BuildConfig
import com.teamb.sj.apod.feature_home.data.remote.dto.PictureDetailDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureDetailApi {
    companion object {
        val BASE_URL = "https://api.nasa.gov/"
        const val path = "planetary/apod"
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("$path?api_key=$API_KEY&thumbs=true")
    suspend fun getPicture(@Query("date") date: String): PictureDetailDto
}