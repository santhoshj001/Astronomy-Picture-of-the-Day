package com.teamb.sj.apod.feature_home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoritesEntity(@PrimaryKey val date: String)
