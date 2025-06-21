package com.example.flightsearch.ui.homeScreenHeader

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.FlightSearchTheme
import com.example.flightsearch.R
import com.example.flightsearch.data.db.entity.Flight
import com.example.flightsearch.ui.homeScreenBody.FlightsList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.factory)
) {
    Surface {
        val uiState by homeScreenViewModel.flightUiState.collectAsState()
        val flights = uiState.flights

        var userSearchFlight by remember { mutableStateOf("") }

        Column (
            modifier = Modifier.fillMaxSize()
                .padding(
                    top = dimensionResource(R.dimen.vertical_padding_medium),
                    start = dimensionResource(R.dimen.horizontal_padding_medium),
                    end = dimensionResource(R.dimen.horizontal_padding_medium),
                    bottom = dimensionResource(R.dimen.padding_medium)
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
                    searchedAirport = uiState.currentSearch ?: Flight(id = 1, iataCode = "", name = "", passengers = 0)
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