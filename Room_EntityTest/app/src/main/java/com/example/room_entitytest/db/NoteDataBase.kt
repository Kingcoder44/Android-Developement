package com.example.room_entitytest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Define the Room database for the application
@Database(
    entities = [Note::class], // The entities associated with this database
    version = 1, // The version of the database schema
    exportSchema = false // Whether to export the database schema to a file
)
abstract class NoteDataBase : RoomDatabase() {

    // Abstract function to get the DAO (Data Access Object) for Notes
    abstract fun getNoteDao(): DAO

    companion object {
        // Name of the database file
        private const val DB_NAME = "notes_db.db"

        // Singleton instance of the database
        private var instance: NoteDataBase? = null

        // Function to get the singleton instance of the database
        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
            // If instance is null, build the database
            instance ?: buildDatabase(context).also {
                // Save the instance for future use
                instance = it
            }
        }

        // Function to build the Room database
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDataBase::class.java, // Class of the database
            DB_NAME // Name of the database file
        ).build()
    }
}
