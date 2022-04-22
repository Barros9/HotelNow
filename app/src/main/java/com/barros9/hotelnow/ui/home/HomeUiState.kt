package com.barros9.hotelnow.ui.home

import com.barros9.hotelnow.domain.models.Hotel

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Error(val errorMessage: String) : HomeUiState()
    data class HasHotels(val hotels: List<Hotel>) : HomeUiState()
}
