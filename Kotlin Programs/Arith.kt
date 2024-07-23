package com.example.first
//program to perform basic arithmetic functions
fun main()
{
    println("Enter two numbers to perform arithmetic functions")
    var n1= readln().toInt()
    var n2= readln().toInt()
    println("Sum of $n1 and $n2 = " +add(n1,n2))
    println("Difference of $n1 and $n2 = " +sub(n1,n2))
    println("Product of $n1 and $n2 = " +pro(n1,n2))
    println("Divison  of $n1 and $n2 = " +div(n1.toDouble(),n2.toDouble()))
    }
fun add(n1: Int,n2:Int):Int
{
return n1+n2
}
fun sub(n1:Int, n2 : Int):Int
{
    return n1-n2
}
fun pro(n1: Int,n2: Int):Int
{
    return n1*n2
}
fun div(n1: Double,n2: Double):Double
{
    return n1/n2
}