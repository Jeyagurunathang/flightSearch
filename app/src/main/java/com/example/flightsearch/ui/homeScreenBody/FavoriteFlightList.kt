package com.example.flightsearch.ui.homeScreenBody

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.flightsearch.R
import com.example.flightsearch.data.db.entity.Favorite
import com.example.flightsearch.data.db.entity.Flight
import com.example.flightsearch.utill.ScreenSizes

@Composable
fun FavoriteFlightList(
    modifier: Modifier = Modifier,
    flights: List<Favorite> = emptyList(),
    currentScreenSize: ScreenSizes,
    removeFavorite: () -> Unit = {}
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        items(
            items = flights,
            key = { it.id }
        ) {
            FavoriteFlightListCard(
                favoriteFlights = it,
                currentScreenSize = currentScreenSize
            )
        }
    }
}