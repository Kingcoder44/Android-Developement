package com.example.room_entitytest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
//    @ColumnInfo(name = "Note name") //if wer want different nanme in android studio and database
    val notename :String,
    val notecont : String,
    @PrimaryKey(autoGenerate = true)
    var noteid : Int=0
)