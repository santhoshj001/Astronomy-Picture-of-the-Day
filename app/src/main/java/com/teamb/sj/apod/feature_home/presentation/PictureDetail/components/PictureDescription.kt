package com.teamb.sj.apod.feature_home.presentation.PictureDetail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail


@Composable
fun PictureDescription(
    picture: PictureDetail?,
    isFav: Boolean,
    onClick: (Boolean) -> Unit
) {
    picture?.let {
        Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                item { PictureDetailHeader(picture) }
                item { PictureDetailBody(picture, isFav, onClick) }
            }
        }
    }
}