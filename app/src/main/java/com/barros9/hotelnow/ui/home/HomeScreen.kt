package com.barros9.hotelnow.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.barros9.hotelnow.R
import com.barros9.hotelnow.domain.models.Hotel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by homeViewModel.uiState

    HomeScreen(
        uiState = uiState,
        onSelectHotel = { },
        onRefreshHotels = { homeViewModel.refreshHotels() }
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onSelectHotel: (Hotel) -> Unit,
    onRefreshHotels: () -> Unit
) {
    when (uiState) {
        HomeUiState.Loading -> ShowLoading()
        is HomeUiState.HasHotels -> ShowList(uiState.hotels, onSelectHotel = onSelectHotel)
        is HomeUiState.Error -> ShowError(onRefreshHotels = onRefreshHotels)
    }
}

@Composable
fun ShowLoading() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowError(
    onRefreshHotels: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.error_message)
        )
        Button(
            onClick = { onRefreshHotels() },
            content = {
                Text(
                    text = stringResource(id = R.string.retry),
                    style = MaterialTheme.typography.body1,
                )
            }
        )
    }
}

@Composable
fun ShowList(
    hotels: List<Hotel>,
    onSelectHotel: (Hotel) -> Unit
) {
    val state = rememberLazyListState()

    LazyColumn(
        state = state
    ) {
        items(hotels) {
            Text(text = it.name)
        }
    }
}