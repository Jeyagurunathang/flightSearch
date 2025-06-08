package com.example.flightsearch.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.flightsearch.data.db.entity.Favorite
import com.example.flightsearch.data.db.entity.Airport
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Query(
        """
            SELECT * FROM airport
            WHERE iata_code LIKE :airportCode 
            OR name LIKE :airportCode
        """
    )
    fun getAirportData(airportCode: String): Flow<List<Airport>>

    @Query(
        """
            SELECT * 
            FROM favorite
        """
    )
    fun getFavoriteAirportData(): Flow<List<Favorite>>
}