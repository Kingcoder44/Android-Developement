package com.example.flashfeed.api

import com.example.flashfeed.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        //singleton instance - objetc can be created only once
        private val retrofit by lazy{
            val logging = HttpLoggingInterceptor() //to log http request and response details
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder() //created with this interceptor
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api by lazy{
            retrofit.create(newsApi :: class.java )
        }
    }
}