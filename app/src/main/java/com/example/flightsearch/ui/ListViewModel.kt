package com.example.flightsearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.repository.FlightRepository
import kotlinx.coroutines.launch

class ListViewModel(
    private val flightRepository: FlightRepository
) : ViewModel() {

    fun getAllFlightData() {
        viewModelScope.launch {
            flightRepository.getAllFlights("FCO")
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                ListViewModel(application.container.flightRepository)
            }
        }
    }
}