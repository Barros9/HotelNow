package com.barros9.hotelnow.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.barros9.hotelnow.R
import com.barros9.hotelnow.domain.model.Hotel
import com.barros9.hotelnow.domain.model.SortType
import com.barros9.hotelnow.ui.home.model.HomeUiState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by homeViewModel.uiState
    val showSortTypeDialog by homeViewModel.showSortTypeDialog
    val sortTypeSelected by homeViewModel.sortTypeSelected
    val isAscending by homeViewModel.isAscending

    HomeContent(
        uiState = uiState,
        onSelectHotel = { hotelId -> navHostController.navigate("detail/$hotelId") },
        onRefreshHotels = { homeViewModel.refreshHotels() },
        showSortTypeDialog = showSortTypeDialog,
        onShowSortTypeDialog = { homeViewModel.showSortTypeDialog(it) },
        sortTypeSelected = sortTypeSelected,
        onSelectSortTypeOption = { homeViewModel.selectSortTypeOption(it) },
        isAscending = isAscending,
        onSelectAscending = { homeViewModel.selectAscending() }
    )
}

@Composable
private fun HomeContent(
    uiState: HomeUiState,
    onSelectHotel: (Long) -> Unit,
    onRefreshHotels: () -> Unit,
    showSortTypeDialog: Boolean,
    onShowSortTypeDialog: (Boolean) -> Unit,
    sortTypeSelected: SortType,
    onSelectSortTypeOption: (SortType) -> Unit,
    isAscending: Boolean,
    onSelectAscending: () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Title()
            SortOption(
                sortTypeSelected = sortTypeSelected,
                onShowSortTypeDialog = onShowSortTypeDialog,
                isAscending = isAscending,
                onSelectAscending = onSelectAscending
            )
            ShowSortDialog(
                sortTypeSelected = sortTypeSelected,
                onSelectSortTypeOption = onSelectSortTypeOption,
                showSortTypeDialog = showSortTypeDialog,
                onShowSortTypeDialog = onShowSortTypeDialog
            )

            when (uiState) {
                HomeUiState.Loading -> ShowLoading()
                is HomeUiState.HasHotels -> ShowHotelsList(
                    hotels = uiState.hotels,
                    onSelectHotel = onSelectHotel
                )
                is HomeUiState.Error -> ShowError(onRefreshHotels = onRefreshHotels)
            }
        }
    }
}

@Composable
private fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            style = MaterialTheme.typography.h3,
            text = stringResource(R.string.hotel_now),
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun SortOption(
    sortTypeSelected: SortType,
    onShowSortTypeDialog: (Boolean) -> Unit,
    isAscending: Boolean,
    onSelectAscending: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        TextButton(
            onClick = { onShowSortTypeDialog(true) }
        ) {
            Text(
                text = stringResource(R.string.sorted_by_with_param, sortTypeSelected.name),
                color = MaterialTheme.colors.primaryVariant
            )
        }

        IconButton(
            onClick = { onSelectAscending() }
        ) {
            Icon(
                modifier = Modifier.size(24.dp, 24.dp),
                imageVector = if (isAscending) Icons.Rounded.ArrowUpward else Icons.Rounded.ArrowDownward,
                contentDescription = null,
                tint = MaterialTheme.colors.primaryVariant,
            )
        }
    }
}

@Composable
private fun ShowSortDialog(
    sortTypeSelected: SortType,
    onSelectSortTypeOption: (SortType) -> Unit,
    showSortTypeDialog: Boolean,
    onShowSortTypeDialog: (Boolean) -> Unit
) {
    var newSortOptionSelected = sortTypeSelected
    if (showSortTypeDialog) {
        AlertDialog(
            onDismissRequest = { onShowSortTypeDialog(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        onSelectSortTypeOption(newSortOptionSelected)
                        onShowSortTypeDialog(false)
                    }
                ) {
                    Text(text = stringResource(R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { onShowSortTypeDialog(false) }
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
            title = { Text(text = stringResource(R.string.sorted_by)) },
            text = { SortOptions(sortTypeSelected) { newSortOptionSelected = it } }
        )
    }
}

@Composable
private fun SortOptions(
    sortTypeSelected: SortType,
    onSelectSortTypeOption: (SortType) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val (sortTypeSelectedRemember, onSelectSortTypeOptionRemember) = remember {
            mutableStateOf(
                sortTypeSelected
            )
        }

        Column {
            SortType.values().forEach { sortType ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (sortType == sortTypeSelectedRemember),
                            onClick = {
                                onSelectSortTypeOptionRemember(sortType)
                                onSelectSortTypeOption(sortType)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = sortType == sortTypeSelectedRemember,
                        onClick = {
                            onSelectSortTypeOptionRemember(sortType)
                            onSelectSortTypeOption(sortType)
                        }
                    )
                    Text(
                        text = sortType.name,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ShowLoading() {
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
private fun ShowError(
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
private fun ShowHotelsList(
    hotels: List<Hotel>,
    onSelectHotel: (Long) -> Unit
) {
    val state = rememberLazyListState()

    LazyColumn(
        state = state
    ) {
        items(hotels) {
            HotelItem(
                hotel = it,
                onSelectHotel = onSelectHotel
            )
        }
    }
}

@Composable
private fun HotelItem(
    hotel: Hotel,
    onSelectHotel: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onSelectHotel(hotel.id) })
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            imageModel = { hotel.gallery.firstOrNull() ?: "" },
            modifier = Modifier
                .width(100.dp)
                .wrapContentHeight(),
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit
            ),
            loading = {
                ImageVector.vectorResource(R.drawable.ic_loading)
            },
            failure = {
                ImageVector.vectorResource(R.drawable.ic_broken_image)
            }
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = hotel.name,
                style = MaterialTheme.typography.h6,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = stringResource(R.string.price),
                            style = MaterialTheme.typography.overline
                        )
                    }
                    Text(
                        text = hotel.price.toString(),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary
                    )
                }
                Column {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            text = stringResource(R.string.rating),
                            style = MaterialTheme.typography.overline
                        )
                    }
                    Text(
                        text = hotel.userRating.toString(),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary
                    )
                }
                Row {
                    repeat(hotel.stars) {
                        Icon(Icons.Filled.Star, "", tint = MaterialTheme.colors.secondaryVariant)
                    }
                }
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "${hotel.location.address} - ${hotel.location.city}",
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
    Divider(thickness = 1.dp)
}
