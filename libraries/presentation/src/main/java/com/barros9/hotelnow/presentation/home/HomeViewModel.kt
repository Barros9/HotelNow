package com.barros9.hotelnow.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barros9.hotelnow.domain.model.Result.Error
import com.barros9.hotelnow.domain.model.Result.Success
import com.barros9.hotelnow.domain.model.SortType
import com.barros9.hotelnow.domain.usecase.GetHotelsUseCase
import com.barros9.hotelnow.presentation.home.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHotelsUseCase: GetHotelsUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = HomeUiState.Error
    }

    private val _uiState by lazy { mutableStateOf<HomeUiState>(HomeUiState.Loading) }
    val uiState: State<HomeUiState> by lazy { _uiState.apply { updateHotels() } }

    private val _sortTypeSelected by lazy { mutableStateOf(SortType.Name) }
    val sortTypeSelected: State<SortType> by lazy { _sortTypeSelected }

    private val _isAscending by lazy { mutableStateOf(true) }
    val isAscending: State<Boolean> by lazy { _isAscending }

    private fun updateHotels() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = HomeUiState.Loading
            getHotelsUseCase(_sortTypeSelected.value, _isAscending.value).collectLatest { result ->
                _uiState.value = when (result) {
                    is Success -> HomeUiState.ShowHotels(result.data)
                    is Error -> HomeUiState.Error
                }
            }
        }
    }

    fun onRetry() {
        updateHotels()
    }

    fun selectSortTypeOption(sortType: SortType) {
        _sortTypeSelected.value = sortType
        updateHotels()
    }

    fun selectAscending() {
        _isAscending.value = !_isAscending.value
        updateHotels()
    }
}