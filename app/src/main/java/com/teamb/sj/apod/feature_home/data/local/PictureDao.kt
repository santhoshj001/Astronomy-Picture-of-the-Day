package com.teamb.sj.apod.feature_home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teamb.sj.apod.feature_home.data.local.entity.FavoritesEntity
import com.teamb.sj.apod.feature_home.data.local.entity.PictureDetailEntity


@Dao
interface PictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(pictureDetail: PictureDetailEntity)

    @Query("DELETE FROM picturedetailentity WHERE date IS :date")
    suspend fun delete(date: String)

    @Query("SELECT * FROM picturedetailentity WHERE date is :date")
    suspend fun getPicture(date: String): PictureDetailEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM favoritesentity WHERE date IS :date")
    suspend fun deleteFavorite(date: String)

    @Query("SELECT * FROM favoritesentity WHERE date IS :date")
    suspend fun isFavorite(date: String): List<FavoritesEntity>

    @Query("SELECT * FROM picturedetailentity where picturedetailentity.date IN (SELECT * FROM FavoritesEntity)")
    suspend fun getFavoritePictures(): List<PictureDetailEntity>
}