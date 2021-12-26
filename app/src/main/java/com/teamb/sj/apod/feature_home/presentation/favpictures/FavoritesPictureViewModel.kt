package com.teamb.sj.apod.feature_home.presentation.favpictures

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamb.sj.apod.core.util.Resource
import com.teamb.sj.apod.feature_home.domain.usecase.GetFavoritePictureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesPictureViewModel @Inject constructor(
    val getFavoritePictureUseCase: GetFavoritePictureUseCase
) : ViewModel() {
    private val _state = mutableStateOf(FavoritePictureState())
    val state: State<FavoritePictureState> = _state

    private var getFavoritePictureJob: Job? = null

    init {
        getFavoritePictures()
    }

    fun getFavoritePictures() {
        getFavoritePictureJob?.cancel()
        getFavoritePictureJob = getFavoritePictureUseCase().onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _state.value = result.data?.let {
                        _state.value.copy(
                            favoritePictures = it,
                            isLoading = false
                        )
                    } ?: FavoritePictureState()
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}