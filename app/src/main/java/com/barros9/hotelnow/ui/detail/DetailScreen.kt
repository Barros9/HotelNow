package com.barros9.hotelnow.ui.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.barros9.hotelnow.domain.models.Hotel

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    hotel: Hotel?
) {

    Text(text = hotel?.name ?: "Error")
}