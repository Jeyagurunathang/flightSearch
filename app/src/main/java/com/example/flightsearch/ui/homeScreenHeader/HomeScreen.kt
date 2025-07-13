package com.example.flightsearch.ui.homeScreenHeader

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.FlightSearchTheme
import com.example.flightsearch.R
import com.example.flightsearch.ui.homeScreenBody.FlightsList
import com.example.flightsearch.utill.ScreenSizes

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.factory)
) {
    Surface {
        val uiState by homeScreenViewModel.flightUiState.collectAsState()
        val flights = uiState.flights
        Log.d("activity", uiState.toString())

        var previouslySearchedFlight: String = ""

        if (flights.isNotEmpty()) {
            LaunchedEffect(Unit) {
                homeScreenViewModel.getFlightsList(flights.first().iataCode)
                previouslySearchedFlight = flights.takeIf { it.isNotEmpty() }?.first()?.iataCode ?: ""
            }
        }
        var userSearchFlight by remember { mutableStateOf(previouslySearchedFlight) }

        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val currentScreenSize = ScreenSizes.getCurrentDeviceScreenSize(windowSizeClass)

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
                    start = dimensionResource(R.dimen.horizontal_padding_medium),
                    end = dimensionResource(R.dimen.horizontal_padding_medium),
                    bottom = dimensionResource(R.dimen.padding_normal)
                )
                .background(color = MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                userSearchingFlight = userSearchFlight,
                onValueChange = {
                    userSearchFlight = it
                    homeScreenViewModel.searchFlights(it.uppercase())
                    homeScreenViewModel.updateIsSearching(true)
                },
                onCancelButtonClicked = { userSearchFlight = "" },
                onSearchTriggered = {
                    homeScreenViewModel.updateCurrentSearch(it)
                    homeScreenViewModel.getFlightsList(it)
                }
            )

            if (uiState.isSearching) {
                SearchSuggestionList(
                    flights = flights,
                    onValueSelected = {
                        userSearchFlight = it
                        homeScreenViewModel.updateCurrentSearch(it)
                        homeScreenViewModel.getFlightsList(it)
                    }
                )
            } else {
                FlightsList(
                    flights = uiState.flights,
                    flightCode = uiState.currentSearchFlightCode,
                    flightDescription = uiState.currentSearchFlightDescription,
                    currentScreenSize = currentScreenSize,
                    makeFavorite = { currentAirportCode, selectedFlightRoute ->
                        homeScreenViewModel.insertFavorite(currentAirportCode, selectedFlightRoute)
                    }
                )
            }

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    FlightSearchTheme {
        HomeScreen()
    }
}