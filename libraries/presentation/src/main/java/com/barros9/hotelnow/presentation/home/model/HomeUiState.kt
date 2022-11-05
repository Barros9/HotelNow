package com.barros9.hotelnow.presentation.home.model

import com.barros9.hotelnow.domain.model.Hotel

sealed class HomeUiState {
    object Loading : HomeUiState()
    object Error : HomeUiState()
    data class HasHotels(val hotels: List<Hotel>) : HomeUiState()
}
