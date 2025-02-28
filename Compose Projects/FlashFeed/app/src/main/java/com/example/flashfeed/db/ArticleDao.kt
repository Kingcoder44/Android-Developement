package com.example.flashfeed.db

// Import necessary AndroidX Room and LiveData libraries
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flashfeed.models.Article

// @Dao annotation marks this interface as a Data Access Object (DAO) for Room Database
@Dao
interface ArticleDao {

    // Inserts an article into the database
    // If there's a conflict (e.g., duplicate primary key), it replaces the existing entry
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article): Long // Returns the inserted row ID

    // Retrieves all articles from the "articles" table
    // LiveData ensures real-time updates when data changes
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    // Deletes a specific article from the database
    @Delete
    suspend fun deleteArticle(article: Article)
}
