package com.example.graduationproject.model.bookmark

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bookmark")
data class BookmarkModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bookmarkId")
    var bookmarkId: Int  = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "country")
    var country: String,

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "description")
    var description :String,

    @ColumnInfo(name = "images")
    var images: String
)
