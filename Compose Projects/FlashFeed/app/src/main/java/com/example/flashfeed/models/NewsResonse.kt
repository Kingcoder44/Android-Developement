package com.example.flashfeed.models

data class NewsResonse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)