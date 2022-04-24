package com.barros9.hotelnow.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.barros9.hotelnow.R
import com.barros9.hotelnow.domain.models.Hotel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    hotel: Hotel
) {
    val pagerState = rememberPagerState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth(),
            state = pagerState,
            count = hotel.gallery.size,
        ) { page ->
            GlideImage(
                imageModel = hotel.gallery[page],
                contentScale = ContentScale.FillWidth,
                placeHolder = ImageVector.vectorResource(R.drawable.ic_loading),
                error = ImageVector.vectorResource(R.drawable.ic_broken_image)
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(16.dp),
        )

        Text(text = hotel.name)
        Text(text = "${hotel.location.address} - ${hotel.location.city}")
        Text(text = "Price")
        Text(text = "${hotel.price} - ${hotel.currency}")
        Text(text = "Review")
        Text(text = hotel.userRating.toString())
        Text(text = "Stars")
        Row {
            repeat(hotel.stars) { Icon(Icons.Filled.Star, "") }
        }
        Text(text = "Contact")
        Text(text = hotel.contact.phoneNumber)
        Text(text = hotel.contact.email)
        Text(text = "CheckIn")
        Text(text = "${hotel.checkIn.from} - ${hotel.checkIn.to}")
        Text(text = "CheckOut")
        Text(text = "${hotel.checkIn.from} - ${hotel.checkIn.to}")
    }
}