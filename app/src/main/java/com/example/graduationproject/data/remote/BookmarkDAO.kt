package com.example.graduationproject.data.remote

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.graduationproject.model.bookmark.BookmarkModel

@Dao
interface BookmarkDAO {
    @Insert
    fun insert(bookmark: BookmarkModel)

    @Delete
    fun delete(bookmark: BookmarkModel)

    @Query("SELECT*FROM bookmark")
    fun getAllBookmark(): List<BookmarkModel>
}