package com.example.flightsearch.data.repository

import com.example.flightsearch.data.db.dao.FlightDao

class FlightRepositoryImpl(
    private val flightDao: FlightDao
) : FlightRepository {
    override suspend fun getAllFlights(flightCode: String) {
        flightDao.getAirportData(airportCode = flightCode)
    }

    override suspend fun getFavoriteFlightDetails() {
        flightDao.getFavoriteAirportData()
    }
}