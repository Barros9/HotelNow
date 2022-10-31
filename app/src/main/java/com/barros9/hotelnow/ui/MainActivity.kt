package com.barros9.hotelnow.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.barros9.hotelnow.ui.detail.DetailScreen
import com.barros9.hotelnow.ui.home.HomeScreen
import com.barros9.hotelnow.ui.theme.HotelNowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            HotelNowTheme {
                NavHost(navController, startDestination = "home") {
                    composable(route = "home") {
                        HomeScreen(navController)
                    }
                    composable(
                        route = "detail/{hotelId}",
                        arguments = listOf(navArgument("hotelId") { type = NavType.LongType })
                    ) {
                        DetailScreen()
                    }
                }
            }
        }
    }
}

