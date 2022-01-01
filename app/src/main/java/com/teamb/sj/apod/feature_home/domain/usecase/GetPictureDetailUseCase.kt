package com.teamb.sj.apod.feature_home.domain.usecase

import com.teamb.sj.apod.core.util.Resource
import com.teamb.sj.apod.core.util.Utils
import com.teamb.sj.apod.feature_home.data.local.prefstore.DataStoreManager
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail
import com.teamb.sj.apod.feature_home.domain.repository.PictureDetailRepository
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPictureDetailUseCase @Inject constructor(
    private val repository: PictureDetailRepository,
    private val dataStoreManager: DataStoreManager
) {
    operator fun invoke(date: Long): Flow<Resource<PictureDetail>> {
        return if (Utils.isValidDate(date)) {
            repository.getPicture(date)
        } else {
            val now = LocalDate.now(ZoneId.of("PST")).toEpochDay()
            repository.getPicture(now)
        }
    }

    suspend fun addFavorite(date: Long) {
        repository.addFavorite(date)
    }

    suspend fun deleteFavorite(date: Long) {
        repository.deleteFavorite(date)
    }

    fun isFavorite(date: Long): Flow<Resource<Boolean>> {
        return repository.isFavorite(date)
    }

    fun getRecentUsedDate(): Flow<Long> {
        return dataStoreManager.getRecentlyUsedDate
    }

    suspend fun setRecentUsedDate(date: Long) {
        dataStoreManager.setRecentlyUsedDate(date)
    }
}
