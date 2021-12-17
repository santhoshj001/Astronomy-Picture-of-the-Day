package com.teamb.sj.apod.feature_home.presentation.favpictures.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.teamb.sj.apod.core.util.Utils
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail

@Composable
fun FavoritePictureItem(
    pictureDetail: PictureDetail,

    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(
                RoundedCornerShape(8.dp)
            )
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column {
            Image(

                painter = rememberImagePainter(pictureDetail.url,
                    builder = {
                        crossfade(true)
                    }),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.0f, false),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier.padding(8.dp)
            ) {
                Text(
                    text = pictureDetail.title,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,

                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = Utils.getHumanReadableDate(pictureDetail.date),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}

/*

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun previewFonts() {

    Surface(modifier = Modifier.fillMaxSize()) {


        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Text(text = "this is h1", style = MaterialTheme.typography.)
            Text(text = "this is h2", style = MaterialTheme.typography.h2)
            Text(text = "this is h3", style = MaterialTheme.typography.h3)
            Text(text = "this is h4", style = MaterialTheme.typography.h4)
            Text(text = "this is h5", style = MaterialTheme.typography.h5)
            Text(text = "this is h6", style = MaterialTheme.typography.h6)
            Text(text = "this is body1", style = MaterialTheme.typography.body1)
            Text(text = "this is body2", style = MaterialTheme.typography.body2)
            Text(text = "this is subtitle1", style = MaterialTheme.typography.subtitle1)
            Text(text = "this is subtitle2", style = MaterialTheme.typography.subtitle2)


        }

    }
}*/
