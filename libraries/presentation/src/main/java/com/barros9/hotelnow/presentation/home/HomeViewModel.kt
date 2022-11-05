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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHotelsUseCase: GetHotelsUseCase
) : ViewModel() {

    private val _uiState by lazy { mutableStateOf<HomeUiState>(HomeUiState.Loading) }
    val uiState: State<HomeUiState> by lazy { _uiState }

    private val _showSortTypeDialog by lazy { mutableStateOf(false) }
    val showSortTypeDialog: State<Boolean> by lazy { _showSortTypeDialog }

    private val _sortTypeSelected by lazy { mutableStateOf(SortType.Name) }
    val sortTypeSelected: State<SortType> by lazy { _sortTypeSelected }

    private val _isAscending by lazy { mutableStateOf(true) }
    val isAscending: State<Boolean> by lazy { _isAscending }

    init {
        refreshHotels()
    }

    fun refreshHotels() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            getHotelsUseCase(_sortTypeSelected.value, _isAscending.value).collectLatest { result ->
                _uiState.value = when (result) {
                    is Success -> HomeUiState.HasHotels(result.data)
                    is Error -> HomeUiState.Error
                }
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