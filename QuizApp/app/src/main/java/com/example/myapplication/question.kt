package com.example.myapplication

data class question(
    val id : Int,
    val questions : String,
    val image  : Int,
     val option1 : String,
    val option2 : String,
    val option3 : String,
    val option4 : String,
    val crct_ans : Int
)
