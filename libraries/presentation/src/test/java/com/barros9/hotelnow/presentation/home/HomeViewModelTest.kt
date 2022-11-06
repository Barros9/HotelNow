package com.barros9.hotelnow.presentation.home

import com.barros9.hotelnow.domain.model.Result.Error
import com.barros9.hotelnow.domain.model.Result.Success
import com.barros9.hotelnow.domain.model.SortType
import com.barros9.hotelnow.domain.usecase.GetHotelsUseCase
import com.barros9.hotelnow.presentation.home.model.HomeUiState
import com.barros9.hotelnow.presentation.mock.PresentationMock
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class HomeViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var homeViewModel: HomeViewModel

    @MockK
    private lateinit var getHotelsUseCase: GetHotelsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(dispatcher)
        homeViewModel = HomeViewModel(getHotelsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `called getHotelsUseCase and returns successfully a list of hotel`() = runTest {
        // Given
        assertEquals(HomeUiState.Loading, homeViewModel.uiState.value)
        coEvery { getHotelsUseCase(any(), any()) } returns flowOf(Success(PresentationMock.listOfHotel))

        // When
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        coVerify { getHotelsUseCase(any(), any()) }
        assertEquals(HomeUiState.ShowHotels(PresentationMock.listOfHotel), homeViewModel.uiState.value)
    }

    @Test
    fun `called getHotelsUseCase and returns an error`() = runTest {
        // Given
        assertEquals(HomeUiState.Loading, homeViewModel.uiState.value)
        coEvery { getHotelsUseCase(any(), any()) } returns flowOf(Error(Exception("Error")))

        // When
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        coVerify { getHotelsUseCase(any(), any()) }
        assertEquals(HomeUiState.Error, homeViewModel.uiState.value)
    }

    @Test
    fun `called getHotelsUseCase and returns an error from exceptionHandler`() = runTest {
        // Given
        assertEquals(HomeUiState.Loading, homeViewModel.uiState.value)
        coEvery { getHotelsUseCase(any(), any()) } throws Exception("Error")

        // When
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        coVerify { getHotelsUseCase(any(), any()) }
        assertEquals(HomeUiState.Error, homeViewModel.uiState.value)
    }

    @Test
    fun `called onRetry and verify getHotelsUseCase was called`() = runTest {
        // Given
        assertEquals(HomeUiState.Loading, homeViewModel.uiState.value)
        coEvery { getHotelsUseCase(any(), any()) } returns flowOf(Success(PresentationMock.listOfHotel))

        // When
        dispatcher.scheduler.advanceUntilIdle()
        homeViewModel.onRetry()

        // Then
        coVerify { getHotelsUseCase(any(), any()) }
    }

    @Test
    fun `called selectSortTypeOption and verify getHotelsUseCase was called`() = runTest {
        // Given
        assertEquals(HomeUiState.Loading, homeViewModel.uiState.value)
        coEvery { getHotelsUseCase(any(), any()) } returns flowOf(Success(PresentationMock.listOfHotel))

        // When
        dispatcher.scheduler.advanceUntilIdle()
        homeViewModel.onSelectSortTypeOption(SortType.Stars)

        // Then
        coVerify { getHotelsUseCase(any(), any()) }
    }

    @Test
    fun `called selectAscending and verify getHotelsUseCase was called`() = runTest {
        // Given
        assertEquals(HomeUiState.Loading, homeViewModel.uiState.value)
        coEvery { getHotelsUseCase(any(), any()) } returns flowOf(Success(PresentationMock.listOfHotel))

        // When
        dispatcher.scheduler.advanceUntilIdle()
        homeViewModel.onSelectAscending()

        // Then
        coVerify { getHotelsUseCase(any(), any()) }
    }

}
