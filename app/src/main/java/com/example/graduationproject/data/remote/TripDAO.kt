package com.example.graduationproject.data.remote

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.graduationproject.model.trip.TripModel

@Dao
interface TripDAO {
    @Insert
    fun insert(tripadd: TripModel)

    @Delete
    fun delete(tripadd: TripModel)

    @Query("SELECT*FROM tripadd")
    fun getAllTrip(): List<TripModel>
}