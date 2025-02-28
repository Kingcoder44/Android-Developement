package com.example.flashfeed.repo

import com.example.flashfeed.api.RetrofitInstance
import com.example.flashfeed.db.ArticleDB
import com.example.flashfeed.models.Article
import java.util.Locale.IsoCountryCode

class NewsRepo(val db : ArticleDB) {
    suspend fun getHeadlines(countryCode: String, pageNumber : Int) =
        RetrofitInstance.api.getHeadlines(countryCode, pageNumber)


    suspend fun searchNews(searchQuery: String, pageNumber : Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)


    suspend fun upsert(article: Article) = db.getArticleDao().insert(article)


    fun getAllArticles() = db.getArticleDao().getAllArticles()

    suspend fun delete(article: Article) = db.getArticleDao().deleteArticle(article)
}