package com.teamb.sj.apod.feature_home.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.teamb.sj.apod.feature_home.data.PictureDetailRepositoryImpl
import com.teamb.sj.apod.feature_home.data.local.PictureDatabase
import com.teamb.sj.apod.feature_home.data.remote.PictureDetailApi
import com.teamb.sj.apod.feature_home.domain.repository.PictureDetailRepository
import com.teamb.sj.apod.feature_home.domain.usecase.GetFavoritePictureUseCase
import com.teamb.sj.apod.feature_home.domain.usecase.GetPictureDetailUseCase
import com.teamb.sj.apod.feature_home.data.local.prefstore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PictureDetailModule {

    @Provides
    @Singleton
    fun provideGetPictureDetailUseCase(repository: PictureDetailRepository): GetPictureDetailUseCase {
        return GetPictureDetailUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetFavoritePictureUseCase(repository: PictureDetailRepository): GetFavoritePictureUseCase {
        return GetFavoritePictureUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePictureDetailRepository(
        pictureDetailApi: PictureDetailApi,
        pictureDatabase: PictureDatabase,
    ): PictureDetailRepository {
        return PictureDetailRepositoryImpl(pictureDetailApi, pictureDatabase.pictureDao)
    }

    @Provides
    @Singleton
    fun providePictureDetailDatabase(application: Application): PictureDatabase {
        return Room.databaseBuilder(application, PictureDatabase::class.java, "picture_db")
            .build()
    }

    @Provides
    @Singleton
    fun providePictureDetailApi(): PictureDetailApi {
        return Retrofit.Builder().baseUrl(PictureDetailApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(PictureDetailApi::class.java)
    }

    @Provides
    @Singleton
    fun providesDataStoreManager(@ApplicationContext appContext: Context): DataStoreManager {
        return DataStoreManager(appContext)
    }

}