package com.example.weatherapplication

import com.example.weatherapplication.data.WeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("/v1/current.json?key=aec320f0d39d44e7a2130847240508")
    suspend fun getWeatherDetail(
        @Query("q") //this will be query parameter "q" or if if would have been key then "key"
        city : String
    ) : Response<WeatherResponseModel>
}