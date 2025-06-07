package com.example.flightsearch.data.db.dao

import androidx.room.Query

interface FavoriteDao {
    @Query(
        """
            SELECT * FROM favorite 
        """
    )
    suspend fun getFavoriteAirportData()
}