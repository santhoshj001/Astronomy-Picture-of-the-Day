package com.teamb.sj.apod.feature_home.presentation.PictureDetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail

@Composable
fun PictureDetailBody(
    picture: PictureDetail,
    isFav: Boolean,
    onClick: (Boolean) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
            Row {
                Text(
                    text = picture.title,
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(
                            bottom = 16.dp
                        )
                        .weight(5f)
                )
                Box(modifier = Modifier.weight(1f)) {
                    FavoriteButton(isBookmarked = isFav,
                        onClick = { onClick(!isFav) })
                }
            }
            Text(
                text = picture.explanation,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 8.dp
                    )
            )
            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}
