package com.example.flashfeed.db

// Import necessary Room and Android context libraries
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.flashfeed.models.Article

// Defines the Room Database with the Article entity and version number
@Database(
    entities = [Article::class],  // Specifies the entities (tables) in the database
    version = 1                   // Database version (increment when modifying the schema)
)
@TypeConverters(Converters::class) // Uses the Converters class for handling custom data types
abstract class ArticleDB : RoomDatabase() {

    // Abstract function to provide DAO for database operations
    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile  // Ensures visibility of changes to the instance across threads
        private var instance: ArticleDB? = null
        private val LOCK = Any()  // Lock object to prevent multiple instances being created

        // Creates the database instance using Room's database builder
        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArticleDB::class.java,
            "article_db.db" // Database name
        ).build()

        // Singleton pattern to ensure only one database instance is created
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }
    }
}
