package com.example.flightsearch.ui.uistate

import com.example.flightsearch.data.db.entity.Flight

data class HomeScreenUiState(
    val flights: List<Flight> = emptyList()
)