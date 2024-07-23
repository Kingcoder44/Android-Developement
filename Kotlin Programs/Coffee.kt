package com.example.first

//program to implement functions in kotlin
fun main()
{
makeCoffee(5,"A")
makeCoffee(1,"B")
makeCoffee(10,"C")
    makeCoffee(0,"D")
}
fun makeCoffee(sugarcount : Int, n : String)
{ if(sugarcount>0)
    if (sugarcount == 1)
        println("Number of sugar is $sugarcount spoon and for $n ")
    else
        println("Number of sugar is $sugarcount spoons and for $n")
    else
        println("Incorrect Input")
}