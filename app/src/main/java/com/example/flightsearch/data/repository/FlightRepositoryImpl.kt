package com.example.flightsearch.data.repository

import com.example.flightsearch.data.db.dao.FlightDao
import com.example.flightsearch.data.db.entity.Favorite
import com.example.flightsearch.data.db.entity.Airport
import kotlinx.coroutines.flow.Flow

class FlightRepositoryImpl(
    private val flightDao: FlightDao
) : FlightRepository {
    override fun getAllFlights(flightCode: String) : Flow<List<Airport>> {
        return flightDao.getAirportData(airportCode = flightCode)
    }

    override fun getFavoriteFlightDetails() : Flow<List<Favorite>> {
        return flightDao.getFavoriteAirportData()
    }
}