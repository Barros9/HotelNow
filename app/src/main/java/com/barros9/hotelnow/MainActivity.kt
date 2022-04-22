package com.barros9.hotelnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                NavHost(navController, startDestination = HotelNowDestinations.HOME_ROUTE) {
                    composable(HotelNowDestinations.HOME_ROUTE) { HomeScreen(navController) }
                }
            }
        }
    }
}

