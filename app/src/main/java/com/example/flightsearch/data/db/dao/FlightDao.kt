package com.example.flightsearch.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flightsearch.data.db.entity.Favorite
import com.example.flightsearch.data.db.entity.Flight
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Query(
        """
            SELECT * FROM airport
            WHERE iata_code NOT LIKE :airportCode
            AND name NOT LIKE :airportCode
        """
    )
    fun getAirportData(airportCode: String): Flow<List<Flight>>

    @Query(
        """
            SELECT * FROM airport
            WHERE iata_code LIKE :iataCode
            OR name LIKE :airportName
        """
    )
    fun getSearchSuggestions(iataCode: String, airportName: String): Flow<List<Flight>>

    @Query(
        """
            SELECT * 
            FROM favorite
        """
    )
    fun getFavoriteAirportData(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)
}