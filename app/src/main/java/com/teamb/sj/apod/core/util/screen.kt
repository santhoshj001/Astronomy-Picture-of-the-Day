package com.teamb.sj.apod.core.util

sealed class Screen(val route: String) {
    object PictureDetailScreen : Screen("picture_screen") {
        fun createRouteWithDate(date: String?): String {
            return route + "?${Constants.PIC_DATE}={${date}}"
        }
    }

    object FavPictureScreen : Screen("favorite_screen")
}