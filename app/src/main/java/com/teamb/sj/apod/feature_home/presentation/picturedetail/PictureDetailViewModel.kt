package com.teamb.sj.apod.feature_home.presentation.picturedetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamb.sj.apod.core.util.Constants
import com.teamb.sj.apod.core.util.Resource
import com.teamb.sj.apod.core.util.Utils
import com.teamb.sj.apod.feature_home.data.local.prefstore.DataStoreManager
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail
import com.teamb.sj.apod.feature_home.domain.usecase.GetPictureDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class PictureDetailViewModel @Inject constructor(
    private val getPictureDetailUseCase: GetPictureDetailUseCase,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private var _searchDateState = mutableStateOf(Constants.DEFAULT_PIC_DATE)
    val searchDate: State<String> = _searchDateState

    private val _state = mutableStateOf(PictureInfoState())
    val state: State<PictureInfoState> = _state

    private val _favState = mutableStateOf(false)
    val favState = _favState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var getPictureDetailJob: Job? = null

    init {
        viewModelScope.launch {
            dataStoreManager.getRecentlyUsedDate.collect { date ->
                if (date!= null && date != Constants.DEFAULT && _searchDateState.value != date) {
                    _searchDateState.value = date
                    search()
                }
            }
        }
    }


    private fun search() {
        //update the recently used date to data store
        viewModelScope.launch {
            dataStoreManager.setRecentlyUsedDate(_searchDateState.value)
        }
        getPictureDetailJob?.cancel()
        getPictureDetailJob = getPictureDetailUseCase(_searchDateState.value).onEach { result ->
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
        getPictureDetailUseCase.isFavorite(_searchDateState.value).onEach { result ->
            when (result) {
                is Resource.Loading<*> -> {
                    _favState.value = false
                }
                is Resource.Success<*> -> {
                    _favState.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

    fun toggleFavorite(isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                getPictureDetailUseCase.addFavorite(_searchDateState.value)
                refreshFavoriteState()
            } else {
                getPictureDetailUseCase.deleteFavorite(_searchDateState.value)
                refreshFavoriteState()
            }
        }
    }

    fun updateDate(localDate: LocalDate?) {
        localDate?.let {
            if (Utils.isFutureDate(localDate)) {
                viewModelScope.launch { _eventFlow.emit(UIEvent.ShowSnackBar("Invalid date - future Date")) }
            } else if (Utils.isOlderDate(localDate)) {
                viewModelScope.launch { _eventFlow.emit(UIEvent.ShowSnackBar("Invalid date - Date Older than 1995-06-16")) }
            } else {
                val updatedDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                updatedDate?.let { _searchDateState.value = it }
                search()
            }
        }
    }


    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}