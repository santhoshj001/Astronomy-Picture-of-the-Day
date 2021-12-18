package com.teamb.sj.apod.feature_home.data

import com.teamb.sj.apod.core.util.Resource
import com.teamb.sj.apod.feature_home.data.local.PictureDao
import com.teamb.sj.apod.feature_home.data.local.entity.FavoritesEntity
import com.teamb.sj.apod.feature_home.data.remote.PictureDetailApi
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail
import com.teamb.sj.apod.feature_home.domain.repository.PictureDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PictureDetailRepositoryImpl @Inject constructor(
    private val api: PictureDetailApi,
    private val dao: PictureDao,
) : PictureDetailRepository {

    override fun getPicture(date: String): Flow<Resource<PictureDetail>> = flow {
        val nullPicture: PictureDetail? = null

        emit(Resource.Loading(nullPicture))
        val databasePicture = dao.getPicture(date)

        if (databasePicture != null) {
            emit(Resource.Success(databasePicture.toPictureDetail()))
        } else {
            try {
                val networkPicture = api.getPicture(date)
                dao.insertPicture(networkPicture.toPictureDetailEntity())
            } catch (e: HttpException) {
                emit(
                    Resource.Error(message = "oops!!! something went wrong", nullPicture)
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "couldn't reach server please check your network connection",
                        nullPicture
                    )
                )
            }
            val updatedPic = dao.getPicture(date = date)?.toPictureDetail()
            emit(Resource.Success(data = updatedPic))
        }
    }

    override suspend fun addFavorite(date: String) {
        dao.insertFavorite(FavoritesEntity(date))
    }

    override suspend fun deleteFavorite(date: String) {
        dao.deleteFavorite(date)
    }


    override fun isFavorite(date: String): Flow<Resource<Boolean>> = flow {
        val a = dao.isFavorite(date)
        if (a.isEmpty())
            emit(Resource.Loading(false))
        else
            emit(Resource.Success(true))
    }

    override fun getFavoritePictures(): Flow<Resource<List<PictureDetail>>> = flow {
        emit(Resource.Loading())
        val pics = dao.getFavoritePictures().map { it.toPictureDetail() }
        if (pics.isNotEmpty()) {
            emit(Resource.Success(data = pics))
        }
    }
}