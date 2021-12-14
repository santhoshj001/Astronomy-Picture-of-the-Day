package com.teamb.sj.apod.feature_home.presentation.PictureDetail.components

import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import com.teamb.sj.apod.R

@Composable
fun FavoriteButton(
    isBookmarked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentAlpha: Float = ContentAlpha.high
) {
    val clickLabel = stringResource(
        if (isBookmarked) R.string.unfavorite else R.string.favorite
    )
    CompositionLocalProvider(LocalContentAlpha provides contentAlpha) {
        IconToggleButton(
            checked = isBookmarked,
            onCheckedChange = { onClick() },
            modifier = modifier.semantics {
                // Use a custom click label that accessibility services can communicate to the user.
                // We only want to override the label, not the actual action, so for the action we pass null.
                this.onClick(label = clickLabel, action = null)
            }
        ) {
            Icon(
                imageVector = if (isBookmarked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = null,
            )
        }
    }
}
