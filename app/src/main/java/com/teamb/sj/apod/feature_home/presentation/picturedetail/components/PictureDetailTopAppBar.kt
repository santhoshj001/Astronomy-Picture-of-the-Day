package com.teamb.sj.apod.feature_home.presentation.picturedetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.teamb.sj.apod.R
import java.util.Locale

@Composable
fun PictureAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavIconPressed: () -> Unit = { },
    titleString: String,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val backgroundColors = TopAppBarDefaults.centerAlignedTopAppBarColors()
    val backgroundColor = backgroundColors.containerColor(
        scrollFraction = scrollBehavior?.scrollFraction ?: 0f
    ).value
    val foregroundColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.Transparent,
        scrolledContainerColor = Color.Transparent
    )
    Box(modifier = Modifier.background(backgroundColor)) {
        CenterAlignedTopAppBar(
            actions = actions,
            title = {
                Text(
                    text = titleString.uppercase(Locale.getDefault()),
                    style = MaterialTheme.typography.displayLarge
                )
            },
            scrollBehavior = scrollBehavior,
            colors = foregroundColors,
            navigationIcon = {
                Box(
                    Modifier.padding(start = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_telescope), "telescope",
                        tint = Color.Unspecified
                    )
                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppBarPreview() {
    AppTheme {
        PictureAppBar(titleString = "Preview")
    }
}

@Preview
@Composable
fun AppBarPreviewDark() {
    AppTheme(useDarkTheme = true) {
        PictureAppBar(titleString = "Preview!")
    }
}
