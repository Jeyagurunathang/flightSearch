package com.example.flightsearch.common

import android.content.Context
import com.example.flightsearch.data.db.FlightSearchDatabase
import com.example.flightsearch.data.repository.FlightRepository
import com.example.flightsearch.data.repository.FlightRepositoryImpl

class DefaultAppContainer(
    private val context: Context
) : FlightAppContainer {
    override val flightRepository: FlightRepository by lazy {
        FlightRepositoryImpl(FlightSearchDatabase.getDatabaseAccess(context).getFlightDao())
    }
}