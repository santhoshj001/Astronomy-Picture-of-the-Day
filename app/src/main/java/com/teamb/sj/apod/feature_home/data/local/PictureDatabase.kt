package com.teamb.sj.apod.feature_home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.teamb.sj.apod.feature_home.data.local.entity.FavoritesEntity
import com.teamb.sj.apod.feature_home.data.local.entity.PictureDetailEntity

@Database(
    entities = [PictureDetailEntity::class, FavoritesEntity::class],
    version = 1
)
abstract class PictureDatabase : RoomDatabase() {
    abstract val pictureDao: PictureDao
}