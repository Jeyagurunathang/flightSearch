package com.example.flightsearch.data.repository

interface FlightRepository {
    suspend fun getAllFlights(flightCode: String)

    suspend fun getFavoriteFlightDetails()
}