package com.teamb.sj.apod.feature_home.domain.usecase

import com.teamb.sj.apod.core.util.Resource
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail
import com.teamb.sj.apod.feature_home.domain.repository.PictureDetailRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritePictureUseCase(
    private val repository: PictureDetailRepository
) {
    operator fun invoke(): Flow<Resource<List<PictureDetail>>> {
        return repository.getFavoritePictures()
    }
}