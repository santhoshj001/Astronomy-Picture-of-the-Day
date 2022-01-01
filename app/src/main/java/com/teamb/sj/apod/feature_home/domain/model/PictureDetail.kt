package com.teamb.sj.apod.feature_home.domain.model

data class PictureDetail(
    val date: Long = 0L,
    val explanation: String = "",
    val mediaType: String = "",
    val title: String = "",
    val url: String = "",
    val hdUrl: String = "",
    val copyright: String = "",
)
