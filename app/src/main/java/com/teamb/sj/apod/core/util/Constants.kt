package com.teamb.sj.apod.core.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import com.teamb.sj.apod.feature_home.presentation.BottomNavItem

object Constants {
    const val FILE_NAME = "Telescope"
    const val FILE_SEPARATOR = "/"
    const val WALLPAPER: String = "wallpaper"
    const val API_KEY: String = "NASA_API_KEY"
    const val DEFAULT_PIC_DATE: String = "Choose Date"
    const val IMAGE: String = "image"
    const val DEFAULT: String = "default"
    const val PIC_DATE = "picDate"
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Outlined.Home,
            route = "picture_screen" + "?picDate={picDate}"
        ),
        BottomNavItem(
            label = "Favorites",
            icon = Icons.Filled.FavoriteBorder,
            route = "favorite_screen"
        )
    )
}
