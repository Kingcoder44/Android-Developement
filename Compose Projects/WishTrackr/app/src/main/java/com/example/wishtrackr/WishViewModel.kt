package com.example.wishtrackr

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishtrackr.data.Wish
import com.example.wishtrackr.data.WishRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel (
    private val wishRepo: WishRepo=Graph.wishRepo
):ViewModel(){

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChange(newString :String)
    {
        wishTitleState = newString
    }
    fun onWishDescriptionChange(newString:String)
    {
        wishDescriptionState  = newString
    }

    fun getWishbyId(id:Long):Flow<Wish>{
        return wishRepo.getAWish(id)
    }

    lateinit var getAllWishes : Flow<List<Wish>>

    init{
        viewModelScope.launch {
            getAllWishes = wishRepo.getWishes()
        }
    }

    fun addWish(wish: Wish)
    {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepo.addAWish(wish=wish)
        }
    }
    fun updateWish(wish: Wish)
    {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepo.updateWish(wish=wish)
        }
    }
    fun deleteWish(wish: Wish)
    {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepo.deleteAWish(wish=wish)
        }
    }
}