package com.example.room_entitytest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

// Data Access Object (DAO) for interacting with the Notes table in the Room database
@Dao
interface DAO {

    // Method to insert a Note into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)//onconflict decides what to do whensame value come as that is alrady presenty in table
    fun insert(note: Note)

    // Method to delete a Note from the database
    @Delete
    fun delete(note: Note)

    // Method to update an existing Note in the database
    @Update
    fun update(note: Note)

    // Query to fetch all Notes from the database
    @Query("SELECT * FROM Notes")
    fun getAllValues(): LiveData<List<Note>>
}
