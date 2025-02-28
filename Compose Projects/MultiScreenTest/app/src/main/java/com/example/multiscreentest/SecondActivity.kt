    package com.example.multiscreentest

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        val fn = intent.getStringExtra("Extra_first_name")
        val ln =intent.getStringExtra("Extra_last_name")
        val tv : TextView = findViewById(R.id.txt)
        println(fn)
        tv.text = "My name is $fn $ln"
    }
}