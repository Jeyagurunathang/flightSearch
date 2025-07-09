package com.example.flightsearch.common

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.flightsearch.data.db.FlightSearchDatabase
import com.example.flightsearch.data.repository.FlightRepository
import com.example.flightsearch.data.repository.FlightRepositoryImpl
import com.example.flightsearch.ui.homeScreenHeader.SearchBarRepository

class DefaultAppContainer(
    private val context: Context
) : FlightAppContainer {

    private val Context.dataStore by preferencesDataStore(name = "search_preference")

    override val flightRepository: FlightRepository by lazy {
        FlightRepositoryImpl(FlightSearchDatabase.getDatabaseAccess(context).getFlightDao())
    }

    override val searchBarRepository: SearchBarRepository = SearchBarRepository(context.dataStore)
}