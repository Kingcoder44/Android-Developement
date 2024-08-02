package com.example.room_entitytest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_entitytest.db.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(private val repo : Repo) : ViewModel() {
  //  val allNotesliveData  = MutableLiveData<List<Note>>()
//aswe get live data we dont use that
    fun getAllNotes() = repo.getAllValue()

    fun insert(note: Note)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            repo.insert(note)
        }

    }
    fun delete(note: Note)
    {
        viewModelScope.launch(Dispatchers.IO)
    {
        repo.delete(note)
    }

    }
    fun update(note: Note)
    {viewModelScope.launch(Dispatchers.IO)
    {
        repo.update(note)
    }

    }
    //as we are using a constructor parameter i view model facotry that ius why we will create an VMfacotry
}