package com.barros9.hotelnow.ui.home

import com.barros9.hotelnow.domain.models.Hotel

sealed class HomeUiState {
    object Loading : HomeUiState()
    object Error : HomeUiState()
    data class HasHotels(val hotels: List<Hotel>) : HomeUiState()
}
