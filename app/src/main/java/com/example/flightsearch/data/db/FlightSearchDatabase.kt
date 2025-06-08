package com.example.flightsearch.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flightsearch.data.db.dao.FlightDao
import com.example.flightsearch.data.db.entity.Favorite
import com.example.flightsearch.data.db.entity.Airport

@Database(entities = [Airport::class, Favorite::class], version = 1)
abstract class FlightSearchDatabase : RoomDatabase() {
    abstract fun getFlightDao(): FlightDao

    companion object {
        @Volatile
        var INSTANCE: FlightSearchDatabase? = null

        fun getDatabaseAccess(context: Context): FlightSearchDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, FlightSearchDatabase::class.java, "flight_search_db")
                    .createFromAsset("database/flight_search.db")
                    .fallbackToDestructiveMigrationFrom()
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }
}