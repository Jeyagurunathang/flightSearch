package com.example.flightsearch.ui.uistate

import com.example.flightsearch.data.db.entity.Favorite
import com.example.flightsearch.data.db.entity.Flight

data class HomeScreenUiState(
    val searchedFlights: List<Flight> = emptyList(),
    val favoriteFlights: List<Favorite> = emptyList(),
    val isSearching: Boolean = false,
    val currentSearchFlightCode: String = "",
    val currentSearchFlightDescription: String = ""
)