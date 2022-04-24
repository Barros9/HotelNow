package com.barros9.hotelnow.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import com.barros9.hotelnow.R
import com.barros9.hotelnow.domain.models.Hotel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    hotel: Hotel
) {
    HorizontalPager(
        count = hotel.gallery.size
    ) { page ->
        GlideImage(
            imageModel = hotel.gallery[page],
            contentScale = ContentScale.FillWidth,
            placeHolder = ImageVector.vectorResource(R.drawable.ic_loading),
            error = ImageVector.vectorResource(R.drawable.ic_broken_image)
        )
    }
}