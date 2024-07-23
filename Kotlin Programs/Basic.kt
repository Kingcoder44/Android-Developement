package com.example.first
fun main()
{
    //using floating data type
    var pi = 3.14
    println(pi)
    pi = 3.141592653595
    println(pi)
    //using boolean data type
    val t: Boolean = true
    val f: Boolean = false
    println(t || f)
    println(t && f)
    println(!t)
    var i=0
    while(i<3)
    {
        println("Value = "+i)
        i++
    }
}