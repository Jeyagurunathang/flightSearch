package com.example.flightsearch.ui.homeScreenHeader

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SearchBarRepository(
    private val datastore: DataStore<Preferences>
) {
    val flightCode = datastore.data.catch {
        emit(emptyPreferences())
    }.map { preferences ->
        preferences[PreferenceKeys.FLIGHT_CODE] ?: ""
    }

    val flightDescription = datastore.data.catch {
        emit(emptyPreferences())
    }.map { preferences ->
        preferences[PreferenceKeys.FLIGHT_DESCRIPTION] ?: ""
    }

    suspend fun saveSearchFlightCode(value: String) {
        datastore.edit { preferences ->
            preferences[PreferenceKeys.FLIGHT_CODE] = value
        }
    }

    suspend fun saveSearchFlightDescription(value: String) {
        datastore.edit { preferences ->
            preferences[PreferenceKeys.FLIGHT_DESCRIPTION] = value
        }
    }

    suspend fun getFlightCode(): String? {
        val preference = datastore.data.first()
        return preference[PreferenceKeys.FLIGHT_CODE] ?: "FCO"
    }

    suspend fun getFlightDescription(): String? {
        val preference = datastore.data.first()
        return preference[PreferenceKeys.FLIGHT_DESCRIPTION] ?: "International Airport"
    }

    suspend fun clearPreferences() {
        datastore.edit { preferences ->
            preferences.clear()
        }
    }
}

object PreferenceKeys {
    val FLIGHT_CODE = stringPreferencesKey("flight_code")
    val FLIGHT_DESCRIPTION = stringPreferencesKey("flight_description")
}