package com.barros9.hotelnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.barros9.hotelnow.presentation.detail.DetailScreen
import com.barros9.hotelnow.presentation.home.HomeScreen
import com.barros9.hotelnow.presentation.theme.HotelNowTheme
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

