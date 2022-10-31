package com.barros9.hotelnow.ui.detail.model

import com.barros9.hotelnow.domain.model.Hotel

internal sealed class DetailUiState {
    object Loading : DetailUiState()
    object Error : DetailUiState()
    data class ShowHotel(val hotel: Hotel) : DetailUiState()
}
