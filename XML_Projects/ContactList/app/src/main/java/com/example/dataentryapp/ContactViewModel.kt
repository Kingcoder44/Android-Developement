package com.example.dataentryapp

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//for live data
class ContactViewModel(private val repo:Repo ):ViewModel() {
    val contactLiveData = MutableLiveData<List<Contact>>()
    init {
        getAllData()
    }
    fun getAllData(){
    val allContact = repo.getAllContact()
        contactLiveData.value = allContact
    }
    fun addContact(contact: Contact){
        repo.addDataToContactList(contact)
    }
}