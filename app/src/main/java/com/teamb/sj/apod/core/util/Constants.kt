package com.teamb.sj.apod.core.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import com.teamb.sj.apod.feature_home.presentation.BottomNavItem

object Constants {
    const val API_KEY: String = "NASA_API_KEY"
    const val DEFAULT_PIC_DATE: String = "Choose Date"
    const val IMAGE: String = "image"
    const val DEFAULT: String = "default"
    const val PIC_DATE = "picDate"
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "picture_screen" + "?picDate={picDate}"
        ),
        BottomNavItem(
            label = "Favorites",
            icon = Icons.Filled.FavoriteBorder,
            route = "favorite_screen"
        )
    )
}
