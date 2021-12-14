package com.teamb.sj.apod.feature_home.domain.usecase

import com.teamb.sj.apod.core.util.Resource
import com.teamb.sj.apod.core.util.Utils
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail
import com.teamb.sj.apod.feature_home.domain.repository.PictureDetailRepository
import kotlinx.coroutines.flow.Flow

class GetPictureDetailUseCase(
    private val repository: PictureDetailRepository,
) {
    operator fun invoke(date: String): Flow<Resource<PictureDetail>> {
        if (date.isBlank() || !Utils.isValidDateFormat(date)) {
            return repository.getPicture("")
        }
        return repository.getPicture(date)
    }

    suspend fun addFavorite(date: String) {
        repository.addFavorite(date)
    }

    suspend fun deleteFavorite(date: String) {
        repository.deleteFavorite(date)
    }

    fun isFavorite(date: String): Flow<Resource<Boolean>> {
        return repository.isFavorite(date)
    }
}