package com.teamb.sj.apod.feature_home.presentation.picturedetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PictureDetailHeader(
    picture: PictureDetail,
    favoriteState: Boolean,
    toggleFav: (Boolean) -> Unit,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { },
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        content = {
            Column {

                Image(
                    painter = rememberImagePainter(
                        picture.url,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth().heightIn(120.dp, 360.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillWidth
                )
                PictureDetailBody(
                    picture,
                    favoriteState,
                    toggleFav
                )
            }
        }
    )
}
