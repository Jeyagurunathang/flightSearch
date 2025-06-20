package com.example.flightsearch.ui.homeScreenHeader

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.repository.FlightRepository
import com.example.flightsearch.ui.uistate.HomeScreenUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val flightRepository: FlightRepository
) : ViewModel() {
    private val _flightUiState = MutableStateFlow(HomeScreenUiState())
    val flightUiState: StateFlow<HomeScreenUiState> = _flightUiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000L)
            searchFlights("FCO")
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

    fun updateIsSearching(isSearching: Boolean) {
        _flightUiState.update {
            it.copy(isSearching = isSearching)
        }
    }

    fun updateCurrentSearch(currentSearch: String) {
        _flightUiState.update {
            it.copy(currentSearch = currentSearch)
        }

        updateIsSearching(false)
        Log.d("uiState", _flightUiState.value.toString())
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                HomeScreenViewModel(flightRepository = application.container.flightRepository)
            }
        }
    }
}