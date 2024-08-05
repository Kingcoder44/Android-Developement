package com.example.weatherapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//this is same for all
class WeatherVMF(private val repo : Repo)
    :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WetherVM(repo) as T
    }

}
