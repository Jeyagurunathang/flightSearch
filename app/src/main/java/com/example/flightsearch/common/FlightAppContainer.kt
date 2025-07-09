package com.example.flightsearch.common

import com.example.flightsearch.data.repository.FlightRepository
import com.example.flightsearch.ui.homeScreenHeader.SearchBarRepository

interface FlightAppContainer {
    val flightRepository: FlightRepository
    val searchBarRepository: SearchBarRepository
}