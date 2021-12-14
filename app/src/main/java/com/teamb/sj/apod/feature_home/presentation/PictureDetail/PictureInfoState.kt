package com.teamb.sj.apod.feature_home.presentation.PictureDetail

import com.teamb.sj.apod.feature_home.domain.model.PictureDetail

data class PictureInfoState(
    val pictureDetail: PictureDetail = PictureDetail(),
    val isLoading: Boolean = false
)