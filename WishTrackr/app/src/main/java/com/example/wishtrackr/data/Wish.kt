package com.example.wishtrackr.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Key Components of Room
//Entity – Defines a table.
//DAO (Data Access Object) – Defines methods to interact with the database.
//Database Class – Creates the database instance.

 @Entity("wish-table")
data class Wish (
     @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
     @ColumnInfo("wish-title")
    val title : String = "",
     @ColumnInfo("wish-desc")
    val descrip  : String=""
)

object dummyWish{
    val wishList = listOf(

        Wish(title="Watch sheldon", descrip = "An android watch"),
        Wish(title = "Food", descrip = "Eat good food"),
        Wish(title = "Get Good", descrip = "")

    )
}