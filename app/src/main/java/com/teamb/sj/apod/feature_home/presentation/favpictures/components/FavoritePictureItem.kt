package com.teamb.sj.apod.feature_home.presentation.favpictures.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail

@Composable
fun FavoritePictureItem(
    pictureDetail: PictureDetail,
    cornerRadius: Dp,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(4.dp),
        elevation = 10.dp,
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius))
        ) {
            Row {
                Image(

                    painter = rememberImagePainter(pictureDetail.url,
                        builder = {
                            crossfade(true)
                        }),
                    contentDescription = null,
                    modifier = Modifier
                        .width(96.dp)
                        .height(96.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            top = 16.dp,
                            bottom = 16.dp,
                            end = 16.dp
                        )
                ) {
                    Text(
                        text = pictureDetail.title,
                        style = MaterialTheme.typography.body1,

                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = pictureDetail.date,
                        style = MaterialTheme.typography.subtitle1,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}