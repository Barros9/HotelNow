package com.barros9.hotelnow.presentation.detail

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.barros9.hotelnow.presentation.R
import com.barros9.hotelnow.presentation.detail.model.DetailUiState
import com.barros9.hotelnow.presentation.mock.PresentationMock
import com.barros9.hotelnow.presentation.theme.HotelNowTheme
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @MockK
    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = true)
    }

    @Test
    fun errorIsDisplayed() {
        // Given
        coEvery { detailViewModel.uiState } returns mutableStateOf(DetailUiState.Error)

        // When
        composeTestRule.setContent {
            HotelNowTheme {
                DetailScreen(detailViewModel = detailViewModel)
            }
        }

        // Then
        composeTestRule.onNodeWithText(context.resources.getString(R.string.detail_error))
            .assertIsDisplayed()
    }

    @Test
    fun hotelIsDisplayed() {
        // Given
        coEvery { detailViewModel.uiState } returns mutableStateOf(DetailUiState.ShowHotel(PresentationMock.listOfHotel[0]))

        // When
        composeTestRule.setContent {
            HotelNowTheme {
                DetailScreen(detailViewModel = detailViewModel)
            }
        }

        // Then
        composeTestRule.onNodeWithText(PresentationMock.listOfHotel[0].name).assertIsDisplayed()
        composeTestRule.onNodeWithText(PresentationMock.listOfHotel[0].contact.email).assertIsDisplayed()
        composeTestRule.onNodeWithText(PresentationMock.listOfHotel[0].contact.phoneNumber).assertIsDisplayed()
    }
}
