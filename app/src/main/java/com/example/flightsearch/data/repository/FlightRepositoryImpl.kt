package com.example.flightsearch.data.repository

import com.example.flightsearch.data.db.dao.FlightDao
import com.example.flightsearch.data.db.entity.Favorite
import com.example.flightsearch.data.db.entity.Flight
import kotlinx.coroutines.flow.Flow

class FlightRepositoryImpl(
    private val flightDao: FlightDao
) : FlightRepository {
    override fun getAllFlights(flightCode: String) : Flow<List<Flight>> {
        return flightDao.getAirportData(airportCode = flightCode)
    }

    override fun getFavoriteFlightDetails() : Flow<List<Favorite>> {
        return flightDao.getFavoriteAirportData()
    }

    override fun getSearchSuggestions(flightCode: String, airportName: String): Flow<List<Flight>> {
        return flightDao.getSearchSuggestions(flightCode, airportName)
    }

    override fun getAirportFlightsData(flightCode: String): Flow<List<Flight>> {
        return flightDao.getAirportData(flightCode)
    }
}