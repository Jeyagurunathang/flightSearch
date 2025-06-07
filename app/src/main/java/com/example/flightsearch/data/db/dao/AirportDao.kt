package com.example.flightsearch.data.db.dao

import androidx.room.Query

interface AirportDao {
    @Query(
        """
            SELECT * FROM airport
            WHERE iata_code LIKE :airportCode 
            OR name LIKE :airportCode
        """
    )
    suspend fun getAirportData(airportCode: String)
}