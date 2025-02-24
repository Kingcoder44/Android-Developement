package com.example.flashfeed.api

import com.example.flashfeed.models.NewsResonse
import com.example.flashfeed.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale.IsoCountryCode

interface newsApi {

    //to fetch url
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        //as we are customizng the url so we will use @Quesry
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey : String = API_KEY
    ) : Response<NewsResonse>
    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey : String = API_KEY
    ) :Response<NewsResonse>
}