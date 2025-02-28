package com.example.mvvmtest

object Repo {
fun getAllBooks() = listOf<Book>(
    Book(
        "A","599","Educational","Someone"
    ),
    Book(
            "B","699","Educational","Someone"
    ),
    Book(
        "C","499","Educational","Someone"
    ),
    Book(
    "D","399","Educational","Someone"
    )
)
}