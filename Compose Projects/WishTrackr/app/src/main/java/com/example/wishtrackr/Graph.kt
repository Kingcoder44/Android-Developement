package com.example.wishtrackr

import android.content.Context
import androidx.room.Room
import com.example.wishtrackr.data.WishDB
import com.example.wishtrackr.data.WishRepo

//to describe the database
object Graph {
    lateinit var db : WishDB

    //lazy ensures all things are not loaded on startup but when needed
    val wishRepo by lazy{
        WishRepo(wishdao = db.wishDao())
    }

    fun provide(context: Context){
        db = Room.databaseBuilder(context,WishDB::class.java,"WishTracker.db").build()
    }
}