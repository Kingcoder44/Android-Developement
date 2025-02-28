package com.example.mvvmtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//data from repo->convert to live data->transfer here to viewmodel
class BookViewModel : ViewModel() {
    val blivedata = MutableLiveData<List<Book>>()
    init{
        getAllBooks()
    }
    fun getAllBooks()
    {
        val allBooks = Repo.getAllBooks()
        //we can perform all businness ligc here //
        blivedata.value = allBooks

    }
}