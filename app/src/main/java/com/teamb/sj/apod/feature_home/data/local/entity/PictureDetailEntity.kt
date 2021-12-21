package com.teamb.sj.apod.feature_home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail

@Entity
data class PictureDetailEntity(
    @PrimaryKey val date: String,
    val explanation: String,
    val mediaType: String,
    val title: String,
    val url: String,
    val copyright: String
) {
    fun toPictureDetail(): PictureDetail {
        return PictureDetail(
            date = date,
            explanation = explanation,
            mediaType = mediaType,
            title = title,
            url = url,
            copyright = copyright
        )
    }
}