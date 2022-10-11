package com.example.graduationproject.model.trip

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tripadd")
data class TripModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tripId")
    var tripId: Int  = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "country")
    var country: String,

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "date1")
    var date1 :String,

    @ColumnInfo(name = "date2")
    var date2 :String,

    @ColumnInfo(name = "images")
    var images: String
)