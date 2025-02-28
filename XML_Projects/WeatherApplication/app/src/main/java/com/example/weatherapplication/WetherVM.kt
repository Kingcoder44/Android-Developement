package com.example.weatherapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.WeatherResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WetherVM(
    private val repo: Repo
) : ViewModel() {
    val weatherLiveData = MutableLiveData<WeatherResponseModel>()
    val loading  = MutableLiveData<Boolean>(false)
    fun getWeatherDetail(city : String)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            //network request sent
            loading.postValue(true)
            val response=repo.getWeatherDetail(city)
            if(response.isSuccessful)
            {
                weatherLiveData.postValue(response.body())
                loading.postValue(false)
            //we have response from derver
            }
        }
    }
}