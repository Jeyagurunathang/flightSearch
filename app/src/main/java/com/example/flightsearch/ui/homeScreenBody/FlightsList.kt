package com.example.flightsearch.ui.homeScreenBody

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.flightsearch.data.db.entity.Flight

@Composable
fun FlightsList(
    modifier: Modifier = Modifier,
    flights: List<Flight> = emptyList()
) {
    FlightListCard(flight = Flight(id = 1, iataCode = "FCO", name = "Leonardo da vinci international Airport", passengers = 21))
}