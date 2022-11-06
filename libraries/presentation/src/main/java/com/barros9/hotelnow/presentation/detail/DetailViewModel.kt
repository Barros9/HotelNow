package com.barros9.hotelnow.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barros9.hotelnow.domain.model.Result.Error
import com.barros9.hotelnow.domain.model.Result.Success
import com.barros9.hotelnow.domain.usecase.GetHotelByIdUseCase
import com.barros9.hotelnow.presentation.detail.model.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getHotelByIdUseCase: GetHotelByIdUseCase
) : ViewModel() {

    private val hotelId
        get() = savedStateHandle.get<Long>("hotelId")
            ?: throw IllegalStateException("Parameter hotelId must not be null!")

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = DetailUiState.Error
    }

    private val _uiState by lazy { mutableStateOf<DetailUiState>(DetailUiState.Loading) }
    internal val uiState: State<DetailUiState> by lazy { _uiState.apply { loadUiState() } }

    private fun loadUiState() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = DetailUiState.Loading
            _uiState.value = when (val response = getHotelByIdUseCase(hotelId)) {
                is Success -> DetailUiState.ShowHotel(response.data)
                is Error -> DetailUiState.Error
            }
        }
    }
}
