package com.example.first
//to show functioning of for loop for a list
fun main()
{
    val a = mutableListOf(1,2,3,4,5)
    for(i in 0..a.size-1) {
        print(a[i])
        a[i] = a[i]*2

    }
    println(a)
}