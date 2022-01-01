package com.teamb.sj.apod.feature_home.domain.repository

import com.teamb.sj.apod.core.util.Resource
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail
import kotlinx.coroutines.flow.Flow

interface PictureDetailRepository {
    fun getPicture(date: Long): Flow<Resource<PictureDetail>>
    suspend fun addFavorite(date: Long)
    suspend fun deleteFavorite(date: Long)
    fun isFavorite(date: Long): Flow<Resource<Boolean>>
    fun getFavoritePictures(): Flow<Resource<List<PictureDetail>>>
}