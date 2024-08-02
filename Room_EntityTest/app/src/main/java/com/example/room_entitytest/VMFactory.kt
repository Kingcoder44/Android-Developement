package com.example.room_entitytest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class VMFactory(private val repo : Repo) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return NotesViewModel(repo) as T

    }
}