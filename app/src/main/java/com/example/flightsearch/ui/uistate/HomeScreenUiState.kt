package com.example.flightsearch.ui.uistate

import com.example.flightsearch.data.db.entity.Airport

data class HomeScreenUiState(
    val flights: List<Airport> = emptyList()
)