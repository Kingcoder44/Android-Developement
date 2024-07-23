package com.example.first
//programm to show implementation of class and object
class Book(var Title: String="Unknown",var author: String="Anonymous", var year: Int=2024)
{
    init {

    }

}
fun main()
{
    var myBook=Book()
    println("Author name is ${myBook.author} and has written the book ${myBook.Title} in year ${myBook.year}")
    var custBook=Book("RDR","Kushagra",2020)
    println("Author name is ${custBook.author} and has written the book ${custBook.Title} in year ${custBook.year}")

}