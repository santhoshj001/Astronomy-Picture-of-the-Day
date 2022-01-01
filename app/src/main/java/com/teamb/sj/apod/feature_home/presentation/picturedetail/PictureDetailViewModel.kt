package com.teamb.sj.apod.feature_home.presentation.picturedetail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamb.sj.apod.core.util.Resource
import com.teamb.sj.apod.core.util.Utils
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail
import com.teamb.sj.apod.feature_home.domain.usecase.GetPictureDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class PictureDetailViewModel @Inject constructor(
    private val getPictureDetailUseCase: GetPictureDetailUseCase
) : ViewModel() {

    private var _searchDateState = mutableStateOf(LocalDate.now())
    val searchDate: State<LocalDate> = _searchDateState

    private val _state = mutableStateOf(PictureInfoState())
    val state: State<PictureInfoState> = _state

    private val _favState = mutableStateOf(false)
    val favState = _favState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var getPictureDetailJob: Job? = null

    init {
        viewModelScope.launch {
            getPictureDetailUseCase.getRecentUsedDate().collect { date ->
                if (Utils.isValidDate(date)) {
                    _searchDateState.value = LocalDate.ofEpochDay(date)
                    search()
                }
            }
        }
    }

    private fun search() {
        // update the recently used date to data store
        Log.i("date", _searchDateState.value.toString())

        viewModelScope.launch {
            getPictureDetailUseCase.setRecentUsedDate(_searchDateState.value.toEpochDay())
        }
        getPictureDetailJob?.cancel()
        getPictureDetailJob =
            getPictureDetailUseCase(_searchDateState.value.toEpochDay()).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            pictureDetail = result.data ?: PictureDetail()
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(result.message ?: "Unknown error"))
                    }
                    is Resource.Loading -> {
                        _state.value = PictureInfoState(
                            pictureDetail = result.data ?: PictureDetail(),
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = PictureInfoState(
                            pictureDetail = result.data ?: PictureDetail()
                        )
                    }
                }
            }.launchIn(viewModelScope)
        refreshFavoriteState()
    }

    private fun refreshFavoriteState() {
        getPictureDetailUseCase.isFavorite(_searchDateState.value.toEpochDay()).onEach { result ->
            when (result) {
                is Resource.Success<*> -> {
                    _favState.value = true
                }
                else -> {
                    _favState.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    fun toggleFavorite(isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                getPictureDetailUseCase.addFavorite(_searchDateState.value.toEpochDay())
                refreshFavoriteState()
            } else {
                getPictureDetailUseCase.deleteFavorite(_searchDateState.value.toEpochDay())
                refreshFavoriteState()
            }
        }
    }

    fun updateDate(localDate: LocalDate?) {
        localDate?.let { date ->
            when {
                Utils.isFutureDate(date) -> {
                    viewModelScope.launch {
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar("Invalid date - future Date")
                        )
                    }
                }
                Utils.isOlderDate(date) -> {
                    viewModelScope.launch {
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar("Invalid date - Date Older than 1995-06-16")
                        )
                    }
                }
                else -> {
                    Log.i("sjdroid", "updateDate: ${date.toString()} ")
                    _searchDateState.value = date
                    search()
                }
            }
        }
    }

    fun sendSnackMessage(message: String) {
        viewModelScope.launch {
            _eventFlow.emit(
                UIEvent.ShowSnackBar(message)
            )
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}
