package com.example.flightsearch.ui.homeScreenHeader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.flightsearch.R
import com.example.flightsearch.data.db.entity.Flight

@Composable
fun SearchSuggestionList(
    modifier: Modifier = Modifier,
    flights: List<Flight>
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_normal))
    ) {
        items(items = flights) { flight ->
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_normal)),
            ){
                Text(
                    text = flight.iataCode,
                    style = MaterialTheme.typography.labelLarge
                )

                Text(
                    text = flight.name,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}