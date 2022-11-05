package com.barros9.hotelnow.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.barros9.hotelnow.presentation.R
import com.barros9.hotelnow.presentation.detail.model.DetailUiState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by detailViewModel.uiState

    DetailContent(
        modifier = Modifier,
        uiState = uiState
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun DetailContent(
    modifier: Modifier,
    uiState: DetailUiState
) {
    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                is DetailUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is DetailUiState.Error -> {
                    Text(stringResource(R.string.detail_error))
                }
                is DetailUiState.ShowHotel -> {
                    val context = LocalContext.current
                    val pagerState = rememberPagerState()
                    val scrollState = rememberScrollState()

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState),
                        verticalArrangement = Arrangement.Top
                    ) {
                        HorizontalPager(
                            modifier = Modifier.fillMaxWidth(),
                            state = pagerState,
                            count = uiState.hotel.gallery.size,
                        ) { page ->
                            GlideImage(
                                imageModel = { uiState.hotel.gallery[page] },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp),
                                imageOptions = ImageOptions(
                                    contentScale = ContentScale.FillBounds
                                ),
                                loading = {
                                    ImageVector.vectorResource(R.drawable.ic_loading)
                                },
                                failure = {
                                    ImageVector.vectorResource(R.drawable.ic_broken_image)
                                }
                            )

                            HorizontalPagerIndicator(
                                pagerState = pagerState,
                                modifier = Modifier.padding(16.dp),
                            )
                        }

                        Column(
                            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
                        ) {
                            Text(
                                style = MaterialTheme.typography.h5,
                                text = uiState.hotel.name
                            )
                            TextButton(
                                onClick = {
                                    val geoUri =
                                        "http://maps.google.com/maps?q=loc:${uiState.hotel.location.latitude},${uiState.hotel.location.longitude}(${uiState.hotel.name})"
                                    context.startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(geoUri)
                                        )
                                    )
                                }
                            ) {
                                Text(text = "${uiState.hotel.location.address} - ${uiState.hotel.location.city}")
                            }
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
                                        text = uiState.hotel.price.toString(),
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
                                        text = uiState.hotel.userRating.toString(),
                                        style = MaterialTheme.typography.body2,
                                        color = MaterialTheme.colors.secondary
                                    )
                                }
                                Row {
                                    repeat(uiState.hotel.stars) {
                                        Icon(
                                            Icons.Filled.Star,
                                            "",
                                            tint = MaterialTheme.colors.secondaryVariant
                                        )
                                    }
                                }
                            }
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
                                            text = stringResource(R.string.phone),
                                            style = MaterialTheme.typography.overline
                                        )
                                    }
                                    Text(
                                        text = uiState.hotel.contact.phoneNumber,
                                        style = MaterialTheme.typography.body2
                                    )
                                }
                                Column {
                                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                        Text(
                                            text = stringResource(R.string.email),
                                            textAlign = TextAlign.Right,
                                            style = MaterialTheme.typography.overline
                                        )
                                    }
                                    Text(
                                        text = uiState.hotel.contact.email,
                                        textAlign = TextAlign.Right,
                                        style = MaterialTheme.typography.body2
                                    )
                                }
                            }
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
                                            text = stringResource(R.string.check_in),
                                            style = MaterialTheme.typography.overline
                                        )
                                    }
                                    Text(
                                        text = "${uiState.hotel.checkIn.from} - ${uiState.hotel.checkIn.to}",
                                        style = MaterialTheme.typography.body2
                                    )
                                }
                                Column {
                                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                        Text(
                                            text = stringResource(R.string.check_out),
                                            textAlign = TextAlign.Right,
                                            style = MaterialTheme.typography.overline
                                        )
                                    }
                                    Text(
                                        text = "${uiState.hotel.checkIn.from} - ${uiState.hotel.checkIn.to}",
                                        textAlign = TextAlign.Right,
                                        style = MaterialTheme.typography.body2
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}