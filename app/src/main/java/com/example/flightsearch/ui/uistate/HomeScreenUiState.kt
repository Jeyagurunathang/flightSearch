package com.example.flightsearch.ui.uistate

import com.example.flightsearch.data.db.entity.Flight

data class HomeScreenUiState(
    val flights: List<Flight> = emptyList(),
    val isSearching: Boolean = false,
    val currentSearchFlightCode: String = "",
    val currentSearchFlightDescription: String = ""
)