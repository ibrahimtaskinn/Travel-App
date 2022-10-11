package com.example.graduationproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.graduationproject.model.trip.TripModel
import com.example.graduationproject.data.remote.TripDAO

@Database(entities = [TripModel::class], version = 1)
abstract class TripDatabase: RoomDatabase() {
    abstract fun tripDao(): TripDAO

    companion object {
        private var instance: TripDatabase? = null

        fun getTripDatabase(context: Context): TripDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    TripDatabase::class.java,
                    "tripadd.db"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}