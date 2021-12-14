package com.teamb.sj.apod.feature_home.domain.repository

import com.teamb.sj.apod.core.util.Resource
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail
import kotlinx.coroutines.flow.Flow

interface PictureDetailRepository {
    fun getPicture(date: String): Flow<Resource<PictureDetail>>
    suspend fun addFavorite(date: String)
    suspend fun deleteFavorite(date: String)
    fun isFavorite(date: String): Flow<Resource<Boolean>>
    fun getFavoritePictures(): Flow<Resource<List<PictureDetail>>>
}