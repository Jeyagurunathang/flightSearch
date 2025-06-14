package com.example.flightsearch.ui.homeScreenHeader

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.FlightSearchTheme
import com.example.flightsearch.R
import com.example.flightsearch.data.db.entity.Flight
import com.example.flightsearch.ui.FlightSearchScreens
import com.example.flightsearch.ui.homeScreenBody.FlightsList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.factory),
    navController: NavHostController = rememberNavController()
) {
    Surface {
        val uiState by homeScreenViewModel.flightUiState.collectAsState()
        val flights = uiState.flights

        var userSearchFlight by remember { mutableStateOf("") }
        var isSearchSuggestionDisplay by remember { mutableStateOf(false) }

        Column (
            modifier = Modifier.fillMaxSize()
                .padding(
                    vertical = dimensionResource(R.dimen.vertical_padding_medium),
                    horizontal = dimensionResource(R.dimen.horizontal_padding_medium)
                )
                .background(color = MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeScreenHeader(
                userSearchingFlight = userSearchFlight,
                onValueChange = {
                    userSearchFlight = it
                    homeScreenViewModel.searchFlights(it.uppercase())
                },
                onCancelButtonClicked = { userSearchFlight = "" }
            )

            if (userSearchFlight.isNotBlank()) {
                SearchSuggestionList(
                    flights = flights
                )
            } else {
                FlightsList()
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