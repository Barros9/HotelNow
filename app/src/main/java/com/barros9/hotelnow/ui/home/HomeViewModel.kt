package com.barros9.hotelnow.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barros9.hotelnow.data.Result
import com.barros9.hotelnow.domain.GetHotelsUseCase
import com.barros9.hotelnow.domain.models.SortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHotelsUseCase: GetHotelsUseCase
) : ViewModel() {

    private val _uiState by lazy { mutableStateOf<HomeUiState>(HomeUiState.Loading) }
    val uiState: State<HomeUiState> by lazy { _uiState }

    private val _showSortTypeDialog by lazy { mutableStateOf(false) }
    val showSortTypeDialog: State<Boolean> by lazy { _showSortTypeDialog }

    private val _sortTypeSelected by lazy { mutableStateOf(SortType.None) }
    val sortTypeSelected: State<SortType> by lazy { _sortTypeSelected }

    private val _isAscending by lazy { mutableStateOf(true) }
    val isAscending: State<Boolean> by lazy { _isAscending }

    init {
        refreshHotels()
    }

    fun refreshHotels() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            _uiState.value = when (val result = getHotelsUseCase(_sortTypeSelected.value, _isAscending.value)) {
                is Result.Success -> HomeUiState.HasHotels(result.data)
                is Result.Error -> HomeUiState.Error
            }
        }
    }

    fun showSortTypeDialog(isOpen: Boolean) {
        _showSortTypeDialog.value = isOpen
    }

    fun selectSortTypeOption(sortType: SortType) {
        _sortTypeSelected.value = sortType
        refreshHotels()
    }

    fun selectAscending() {
        _isAscending.value = !_isAscending.value
        refreshHotels()
    }
}