package com.teamb.sj.apod.feature_home.data

import com.teamb.sj.apod.core.util.FirebaseUtils
import com.teamb.sj.apod.core.util.Resource
import com.teamb.sj.apod.core.util.Utils
import com.teamb.sj.apod.feature_home.data.local.PictureDao
import com.teamb.sj.apod.feature_home.data.local.entity.FavoritesEntity
import com.teamb.sj.apod.feature_home.data.remote.PictureDetailApi
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail
import com.teamb.sj.apod.feature_home.domain.repository.PictureDetailRepository
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PictureDetailRepositoryImpl @Inject constructor(
    private val api: PictureDetailApi,
    private val dao: PictureDao,
) : PictureDetailRepository {

    override fun getPicture(date: Long): Flow<Resource<PictureDetail>> = flow {
        val nullPicture: PictureDetail? = null
        emit(Resource.Loading(nullPicture))
        val databasePicture = dao.getPicture(date)
        if (databasePicture != null) {
            emit(Resource.Success(databasePicture.toPictureDetail()))
        } else {
            try {
                val startDate = Utils.getStartDateOfMonth(date)
                val endDate = Utils.getEndDateOfMonth(date)
                val networkPictures = api.getPicture(
                    startDate = startDate,
                    endDate = endDate,
                    apiKey = FirebaseUtils.getApiKey()
                )
                for (picture in networkPictures)
                    dao.insertPicture(picture.toPictureDetailEntity())
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

    override suspend fun addFavorite(date: Long) {
        dao.insertFavorite(FavoritesEntity(date))
    }

    override suspend fun deleteFavorite(date: Long) {
        dao.deleteFavorite(date)
    }

    override fun isFavorite(date: Long): Flow<Resource<Boolean>> = flow {
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
