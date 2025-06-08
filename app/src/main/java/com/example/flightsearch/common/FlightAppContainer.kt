package com.example.flightsearch.common

import com.example.flightsearch.data.repository.FlightRepository

interface FlightAppContainer {
    val flightRepository: FlightRepository
}