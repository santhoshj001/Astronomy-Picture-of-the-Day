package com.teamb.sj.apod.feature_home.presentation.picturedetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail


@Composable
fun PictureDetailHeader(picture: PictureDetail) {
    Image(
        painter = rememberImagePainter(picture.url,
            builder = {
                crossfade(true)
            }),
        contentDescription = "",
        modifier = Modifier
            .aspectRatio(1.0f, false)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp)),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
}

