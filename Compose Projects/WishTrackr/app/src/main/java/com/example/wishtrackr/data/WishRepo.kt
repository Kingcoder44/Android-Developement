package com.example.wishtrackr.data

import kotlinx.coroutines.flow.Flow

class WishRepo(private val wishdao : WishDAO) {

    suspend fun addAWish(wish:Wish)
    {
        wishdao.addAWish(wish)
    }
    fun getWishes(): Flow<List<Wish>> = wishdao.fetchWish()

    fun getAWish(id:Long):Flow<Wish>{
        return wishdao.getWishesById(id)
    }

    suspend fun updateWish(wish: Wish)
    {
        wishdao.updateAll(wish)
    }
    suspend fun deleteAWish(wish: Wish)
    {
        wishdao.delete(wish)
    }

}