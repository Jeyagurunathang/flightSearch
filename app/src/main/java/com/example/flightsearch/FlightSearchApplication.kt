package com.example.flightsearch

import android.app.Application
import com.example.flightsearch.common.DefaultAppContainer

class FlightSearchApplication : Application() {
    lateinit var container: DefaultAppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}