package com.example.multiscreentest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val next_page_btn : Button = findViewById(R.id.btn)
        val fname: EditText = findViewById(R.id.fn)
        val lname: EditText = findViewById(R.id.ln)
        //to use the new created activity i.e. to connect two pages we are using a button and use intent on that button
        next_page_btn.setOnClickListener {
            //intent uses two pararmeters one is the source and other is the destination
            //curent source denoted by this, and destination with acitivity name::class.java
            val edtf = fname.text.toString()
            val edtl = lname.text.toString()
            val inte = Intent(this,SecondActivity::class.java)
            inte.putExtra("Extra_first_name",edtf)
            inte.putExtra("Extra_last_name",edtl)
            startActivity(inte)
        }
    }
}