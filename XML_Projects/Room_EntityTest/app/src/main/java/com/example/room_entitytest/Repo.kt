package com.example.room_entitytest

import androidx.room.Dao
import com.example.room_entitytest.db.DAO
import com.example.room_entitytest.db.Note

class Repo(private val dao : DAO) {
    fun insert (note : Note)
    {
        dao.insert(note)
    }
    fun delete(note:Note)
    {
        dao.delete(note)
    }
    fun update(note:Note)
    {
        dao.update(note)
    }
    fun getAllValue() = dao.getAllValues()
}