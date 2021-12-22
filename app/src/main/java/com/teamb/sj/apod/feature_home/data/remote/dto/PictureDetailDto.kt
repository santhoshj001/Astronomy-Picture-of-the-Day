package com.teamb.sj.apod.feature_home.data.remote.dto

import androidx.annotation.Keep
import com.teamb.sj.apod.core.util.Constants
import com.teamb.sj.apod.feature_home.data.local.entity.PictureDetailEntity
@Keep
data class PictureDetailDto(
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String,
    val thumbnail_url: String,
    val copyright: String?
) {
    fun toPictureDetailEntity(): PictureDetailEntity {
        return PictureDetailEntity(
            date = date,
            explanation = explanation,
            mediaType = media_type,
            title = title,
            url = if (media_type == Constants.IMAGE) url else thumbnail_url,
            copyright = copyright ?: "Copyright Free"
        )
    }
}
