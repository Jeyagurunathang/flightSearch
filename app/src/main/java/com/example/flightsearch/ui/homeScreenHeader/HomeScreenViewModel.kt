package com.example.flightsearch.ui.homeScreenHeader

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.db.entity.Favorite
import com.example.flightsearch.data.db.entity.Flight
import com.example.flightsearch.data.repository.FlightRepository
import com.example.flightsearch.ui.uistate.HomeScreenUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val flightRepository: FlightRepository,
    private val userSearchRepository: SearchBarRepository
) : ViewModel() {
    private val _flightUiState = MutableStateFlow(HomeScreenUiState())
    val flightUiState: StateFlow<HomeScreenUiState> = _flightUiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000L)
            searchFlights(userSearchRepository.getFlightCode() ?: "FCO")
        }
    }

    fun searchFlights(flightCode: String) {
        val iataCode = "$flightCode%"
        val airportName = "%$flightCode%"
        viewModelScope.launch {
            flightRepository.getSearchSuggestions(iataCode, airportName).collect { flights ->
                _flightUiState.update {
                    it.copy(flights = flights)
                }
            }
        }
    }

    fun getFlightsList(flightCode: String) {
        viewModelScope.launch {
            flightRepository.getAirportFlightsData(flightCode).collect { flights ->
                _flightUiState.update { it.copy(flights = flights) }
            }

            Log.d("uiState", _flightUiState.value.toString())
        }
    }

    fun updateIsSearching(isSearching: Boolean) {
        _flightUiState.update {
            it.copy(isSearching = isSearching)
        }
    }

    fun updateCurrentSearch(currentSearch: String) {
        val searchedFlight = _flightUiState.value.flights.first { it.iataCode == currentSearch }

        viewModelScope.launch {
            userSearchRepository.saveSearchFlightCode(searchedFlight.iataCode)
            userSearchRepository.saveSearchFlightDescription(searchedFlight.name)

            _flightUiState.update {
                it.copy(
                    currentSearchFlightCode = userSearchRepository.getFlightCode() ?: "",
                    currentSearchFlightDescription = userSearchRepository.getFlightDescription() ?: ""
                )
            }
        }

        updateIsSearching(false)
    }

    fun insertFavorite(currentAirportCode: String, selectedFlightRoute: Flight) {
        val favorite = Favorite(id = selectedFlightRoute.id, departureCode = currentAirportCode, destinationCode = selectedFlightRoute.iataCode)
        viewModelScope.launch {
            flightRepository.insertFavorite(favorite)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                HomeScreenViewModel(
                    flightRepository = application.container.flightRepository,
                    userSearchRepository = application.container.searchBarRepository
                )
            }
        }
    }
}