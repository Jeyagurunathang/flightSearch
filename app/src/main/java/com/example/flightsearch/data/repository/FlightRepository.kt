package com.example.flightsearch.data.repository

import com.example.flightsearch.data.db.entity.Favorite
import com.example.flightsearch.data.db.entity.Flight
import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun getAllFlights(flightCode: String): Flow<List<Flight>>

    fun getFavoriteFlightDetails(): Flow<List<Favorite>>

    fun getSearchSuggestions(flightCode: String, airportName: String): Flow<List<Flight>>

    fun getAirportFlightsData(flightCode: String): Flow<List<Flight>>

    suspend fun insertFavorite(favorite: Favorite)
}