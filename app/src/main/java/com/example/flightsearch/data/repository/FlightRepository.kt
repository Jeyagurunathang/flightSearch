package com.example.flightsearch.data.repository

import com.example.flightsearch.data.db.entity.Favorite
import com.example.flightsearch.data.db.entity.Flight
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun getAllFlights(flightCode: String): Flow<List<Flight>>

    fun getFavoriteFlightDetails(): Flow<List<Favorite>>
}