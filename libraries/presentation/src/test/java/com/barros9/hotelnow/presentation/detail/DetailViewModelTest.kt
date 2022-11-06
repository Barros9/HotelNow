package com.barros9.hotelnow.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.barros9.hotelnow.domain.model.Result.Error
import com.barros9.hotelnow.domain.model.Result.Success
import com.barros9.hotelnow.domain.usecase.GetHotelByIdUseCase
import com.barros9.hotelnow.presentation.detail.model.DetailUiState
import com.barros9.hotelnow.presentation.mock.PresentationMock
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class DetailViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var detailViewModel: DetailViewModel

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    @MockK
    private lateinit var getHotelByIdUseCase: GetHotelByIdUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(dispatcher)
        detailViewModel = DetailViewModel(
            savedStateHandle = savedStateHandle,
            getHotelByIdUseCase = getHotelByIdUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `called getHotelByIdUseCase and returns successfully a hotel object`() = runTest {
        // Given
        assertEquals(DetailUiState.Loading, detailViewModel.uiState.value)
        coEvery { savedStateHandle.get<Long>("hotelId") } returns 1
        coEvery { getHotelByIdUseCase(any()) } returns Success(PresentationMock.listOfHotel[1])

        // When
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(DetailUiState.ShowHotel(PresentationMock.listOfHotel[1]), detailViewModel.uiState.value)
    }

    @Test
    fun `called getHotelByIdUseCase and returns an error`() = runTest {
        // Given
        assertEquals(DetailUiState.Loading, detailViewModel.uiState.value)
        coEvery { savedStateHandle.get<Long>("hotelId") } returns 1
        coEvery { getHotelByIdUseCase(any()) } returns Error(Exception("Error"))

        // When
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(DetailUiState.Error, detailViewModel.uiState.value)
    }

    @Test
    fun `called getHotelByIdUseCase and returns an error from exceptionHandler`() = runTest {
        // Given
        assertEquals(DetailUiState.Loading, detailViewModel.uiState.value)
        coEvery { savedStateHandle.get<Long>("hotelId") } returns 1
        coEvery { getHotelByIdUseCase(any()) } throws Exception("Error")

        // When
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(DetailUiState.Error, detailViewModel.uiState.value)
    }
}
