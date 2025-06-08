package com.example.flightsearch.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.ui.theme.FlightSearchTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.factory)
) {
    Scaffold { innerPadding ->
        val uiState by homeScreenViewModel.homeScreenUiState.collectAsState()
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(uiState.flights.toString())
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