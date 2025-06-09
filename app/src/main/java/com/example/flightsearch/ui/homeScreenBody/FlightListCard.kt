package com.example.flightsearch.ui.homeScreenBody

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flightsearch.R
import com.example.flightsearch.data.db.entity.Flight

@Composable
fun FlightListCard(
    modifier: Modifier = Modifier,
    flight: Flight
) {
    Card (
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column (
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_normal))
        ) {
            Text(
                text = flight.iataCode,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = flight.name,
                style = MaterialTheme.typography.bodySmall
            )

            Icon(
                painter = painterResource(R.drawable.airplane),
                contentDescription = "flight icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.padding_regular))
                    .rotate(90F)
                    .size(16.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "CHE",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Chennai International Airport",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}