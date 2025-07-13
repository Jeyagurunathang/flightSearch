package com.example.flightsearch.ui.homeScreenBody

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flightsearch.R
import com.example.flightsearch.data.db.entity.Favorite
import com.example.flightsearch.data.db.entity.Flight
import com.example.flightsearch.utill.ScreenSizes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FlightListCard(
    modifier: Modifier = Modifier,
    flight: Flight,
    currentSearchedFlightCode: String = "",
    currentSearchedFlightDescription: String = "",
    currentScreenSize: ScreenSizes,
    makeFavorite: (String, Flight) -> Unit
) {
    val scope = rememberCoroutineScope()
    var isFavorite by remember { mutableStateOf(false) }
    val scale = remember { Animatable(1F) }
    Card (
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Box {
            IconButton(
                onClick = {
                    isFavorite = !isFavorite

                    scope.launch {
                        scale.animateTo(
                            targetValue = 1.3F,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioHighBouncy,
                                stiffness = Spring.StiffnessMedium
                            )
                        )
                        delay(2)
                        scale.animateTo(
                            targetValue = 1F,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        )
                    }

                    makeFavorite(currentSearchedFlightCode, flight)
                },
                modifier = Modifier.align(Alignment.TopEnd).scale(scale.value)
            ) {
                Icon(
                    painter = if (isFavorite) painterResource(R.drawable.filled_heart) else painterResource(R.drawable.outline_heart),
                    contentDescription = "Heart",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            when (currentScreenSize) {
                ScreenSizes.MOBILE_PORTRAIT -> {
                    Column (
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
                        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_normal))
                    ) {
                        Text(
                            text = currentSearchedFlightCode,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = currentSearchedFlightDescription,
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
                            text = flight.iataCode,
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            text = flight.name,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                else -> {
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(dimensionResource(R.dimen.padding_medium)),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = currentSearchedFlightCode,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                text = currentSearchedFlightDescription,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Icon(
                            painter = painterResource(R.drawable.airplane),
                            contentDescription = "flight icon",
                            modifier = Modifier
                                .padding(vertical = dimensionResource(R.dimen.padding_regular))
                                .size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Column {
                            Text(
                                text = flight.iataCode,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                text = flight.name,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}