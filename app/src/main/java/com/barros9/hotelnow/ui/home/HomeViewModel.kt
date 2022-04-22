package com.barros9.hotelnow.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barros9.hotelnow.data.Result
import com.barros9.hotelnow.domain.GetHotelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHotelsUseCase: GetHotelsUseCase
) : ViewModel() {

    private val _uiState by lazy { mutableStateOf<HomeUiState>(HomeUiState.Loading) }
    val uiState: State<HomeUiState> by lazy { _uiState }

    init {
        refreshHotels()
    }

    fun refreshHotels() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            _uiState.value = when (val result = getHotelsUseCase()) {
                is Result.Success -> HomeUiState.HasHotels(result.data)
                is Result.Error -> HomeUiState.Error
            }
        }
    }
}