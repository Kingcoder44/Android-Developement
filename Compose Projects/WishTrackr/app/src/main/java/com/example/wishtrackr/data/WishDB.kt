package com.example.wishtrackr.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false

)
abstract class WishDB : RoomDatabase() {
abstract fun wishDao() : WishDAO
}