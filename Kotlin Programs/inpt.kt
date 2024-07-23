package com.example.first
//use of if-else and input from user
fun main()
{
    println("Enter your age")
    var age = readln().toInt()
    if (age>=18)
        println("Eligible to vote")
    else
        println("Not eligible")
}