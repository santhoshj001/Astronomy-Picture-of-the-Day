package com.teamb.sj.apod.feature_home.presentation.picturedetail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
    onClick: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = picture.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "\u00a9 " + picture.copyright,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        ExpandableText(text = picture.explanation, modifier = Modifier.padding(horizontal = 8.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(onClick = { onClick(!isFav) }, content = {
                val imageVector =
                    if (isFav) Icons.Filled.Favorite
                    else Icons.Outlined.FavoriteBorder
                Icon(
                    imageVector,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                val label = if (isFav) "Added to Favorites" else "Add to Favorites"
                Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                Text(
                    text = label,
                )
            })
            val visibility = false
            if (visibility) {
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(onClick = { }) {
                    Text(
                        text = "Set as Wallpaper",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
