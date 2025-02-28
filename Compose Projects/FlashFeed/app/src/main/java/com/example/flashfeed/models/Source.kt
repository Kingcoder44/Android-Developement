package com.example.flashfeed.models

data class Source(
    val id: String? = null,
    val name: String
){
    override fun hashCode(): Int {
        return (id ?: "").hashCode() // Prevent null crash
    }
}