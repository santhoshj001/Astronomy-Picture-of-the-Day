package com.teamb.sj.apod.feature_home.presentation.picturedetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = picture.title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(5f)
                )
                Box(modifier = Modifier.weight(1f)) {
                    FavoriteButton(checked = isFav,
                        onClick = { onClick(!isFav) })
                }
            }
            Text(
                text = picture.explanation,
                style = MaterialTheme.typography.bodySmall,
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
