package com.teamb.sj.apod.feature_home.presentation.favpictures

import com.teamb.sj.apod.feature_home.domain.model.PictureDetail

data class FavoritePictureState(
    val favoritePictures: List<PictureDetail> = emptyList(),
    val isLoading: Boolean = false
)