package com.teamb.sj.apod.feature_home.presentation.PictureDetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail


@Composable
fun PictureDetailHeader(picture: PictureDetail) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(picture.url,
                builder = {
                    crossfade(true)
                }),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp),
            contentScale = ContentScale.Crop
        )

    }
}
