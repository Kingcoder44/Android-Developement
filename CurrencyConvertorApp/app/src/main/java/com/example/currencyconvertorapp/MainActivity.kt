package com.example.currencyconvertorapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //step 1: to collevt all views to kotlin file
        //to connect we use findViewById(R.id.id to find)
        //these are variable to refer to the views or components
        val txt : EditText = findViewById(R.id.EDT)
        val btnRu : Button = findViewById(R.id.rupee)
        val btnDo : Button = findViewById(R.id.dollar)
        val res: TextView = findViewById(R.id.result)

        btnRu.setOnClickListener {
            val amount = txt.text.toString().toInt()
            val result = amount * 80
            res.text = "Converted Amount to Rupees : $result"
        }
        btnDo.setOnClickListener {
            val amnt= txt.text.toString().toInt()
            val result = amnt/80
            res.text = "Converted Amount to Dollars : $result"
        }

    }
}
