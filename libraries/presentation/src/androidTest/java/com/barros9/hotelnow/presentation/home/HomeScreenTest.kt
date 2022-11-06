package com.barros9.hotelnow.presentation.home

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.barros9.hotelnow.domain.model.SortType
import com.barros9.hotelnow.presentation.R
import com.barros9.hotelnow.presentation.home.model.HomeUiState
import com.barros9.hotelnow.presentation.mock.PresentationMock
import com.barros9.hotelnow.presentation.theme.HotelNowTheme
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @MockK
    private lateinit var navHostController: NavHostController

    @MockK
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = true)
    }

    @Test
    fun errorIsDisplayed() {
        // Given
        coEvery { homeViewModel.uiState } returns mutableStateOf(HomeUiState.Error)
        coEvery { homeViewModel.sortTypeSelected } returns mutableStateOf(SortType.Name)
        coEvery { homeViewModel.isAscending } returns mutableStateOf(true)

        // When
        composeTestRule.setContent {
            HotelNowTheme {
                HomeScreen(navHostController, homeViewModel)
            }
        }

        // Then
        composeTestRule.onNodeWithText(context.resources.getString(R.string.retry)).assertIsDisplayed()
    }

    @Test
    fun hotelListIsDisplayed() {
        // Given
        coEvery { homeViewModel.uiState } returns mutableStateOf(HomeUiState.ShowHotels(PresentationMock.listOfHotel))
        coEvery { homeViewModel.sortTypeSelected } returns mutableStateOf(SortType.Name)
        coEvery { homeViewModel.isAscending } returns mutableStateOf(true)

        // When
        composeTestRule.setContent {
            HotelNowTheme {
                HomeScreen(navHostController, homeViewModel)
            }
        }

        // Then
        composeTestRule.onNodeWithText(PresentationMock.listOfHotel[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(PresentationMock.listOfHotel[1].name).assertIsDisplayed()
    }

    @Test
    fun onClickSelectHotel() {
        // Given
        val hotelId = PresentationMock.listOfHotel[0].id
        coEvery { homeViewModel.uiState } returns mutableStateOf(HomeUiState.ShowHotels(PresentationMock.listOfHotel))
        coEvery { navHostController.navigate("route", any(), any()) } returns Unit
        coEvery { homeViewModel.sortTypeSelected } returns mutableStateOf(SortType.Name)
        coEvery { homeViewModel.isAscending } returns mutableStateOf(true)

        // When
        composeTestRule.setContent {
            HotelNowTheme {
                HomeScreen(navHostController, homeViewModel)
            }
        }

        // Then
        composeTestRule.onNodeWithText(PresentationMock.listOfHotel[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(PresentationMock.listOfHotel[0].name).performClick()

        coVerify { navHostController.navigate("detail/$hotelId", any(), any()) }
    }

    @Test
    fun onClickRetry() {
        // Given
        coEvery { homeViewModel.uiState } returns mutableStateOf(HomeUiState.Error)
        coEvery { homeViewModel.sortTypeSelected } returns mutableStateOf(SortType.Name)
        coEvery { homeViewModel.isAscending } returns mutableStateOf(true)

        // When
        composeTestRule.setContent {
            HotelNowTheme {
                HomeScreen(navHostController, homeViewModel)
            }
        }

        // Then
        composeTestRule.onNodeWithText(context.resources.getString(R.string.retry)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.retry)).performClick()

        coVerify { homeViewModel.onRetry() }
    }
}
