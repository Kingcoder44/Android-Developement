package com.example.weatherapplication

class Repo(
    private val retrofitServices: RetrofitServices
) {
    suspend fun getWeatherDetail(city : String) = retrofitServices.getWeatherDetail(city)

}