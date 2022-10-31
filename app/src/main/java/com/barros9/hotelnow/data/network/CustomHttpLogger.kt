package com.barros9.hotelnow.data.network

import android.util.Log
import io.ktor.client.features.logging.Logger

internal class CustomHttpLogger : Logger {
    override fun log(message: String) {
        Log.d("loggerTag", message)
    }
}