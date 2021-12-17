package com.teamb.sj.apod.feature_home.presentation.picturedetail.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail


@Composable
fun PictureDescription(
    picture: PictureDetail?,
    isFav: Boolean,
    onClick: (Boolean) -> Unit
) {
    picture?.let {
        LazyColumn {
            item { PictureDetailHeader(picture) }
            item { PictureDetailBody(picture, isFav, onClick) }
        }

    }
}