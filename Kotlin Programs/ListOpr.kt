package com.example.first
//program to show operations of List
fun main()
{
    val fruitList= mutableListOf("apple","orange","grape","melon","mango")
    println(fruitList)
    fruitList.add("banana")
    println(fruitList)
    fruitList.remove("apple")
    println(fruitList.contains("melon"))
}
