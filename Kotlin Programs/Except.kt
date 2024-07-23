package com.example.first
//to show functionality of exception handling in kotlin
fun main() {
    try {
        println("Enter number")
        var n = readln().toInt()
        println("Number entered is ${n}")
    }
    catch(e:Exception) //to create objext fo exception
    {
       // println(e.message)
        println("Only numbers allowed")
    }
    finally{
        println("Will be executed regardless of exception")
    }
}