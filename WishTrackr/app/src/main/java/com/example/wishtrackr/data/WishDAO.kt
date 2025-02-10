package com.example.wishtrackr.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


//To define Provides methods to interact with the database.
@Dao
abstract class WishDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAWish(wishEntity: Wish)
    //loads all wishes from table we created
    @Query("Select * from `wish-table`")
    abstract fun fetchWish() : Flow<List<Wish>>

    @Update
    abstract suspend fun updateAll(wishEntity: Wish)

    @Delete
    abstract suspend fun delete(wishEntity: Wish)
    @Query("Select * from `wish-table` where id=:id")
    abstract fun getWishesById(id : Long):Flow<Wish>




}