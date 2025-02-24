package com.example.flashfeed.db

// Import necessary Room library for TypeConverters
import androidx.room.TypeConverter
import com.example.flashfeed.models.Source

// A class that contains TypeConverters for Room Database
class Converters {

    // Converts a Source object to a String (to be stored in the database)
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name  // Extracts the name property from the Source object
    }

    // Converts a String back to a Source object (when retrieving data from the database)
    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)  // Creates a Source object with the same name for both fields
    }
}
