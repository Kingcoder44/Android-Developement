package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btn_start : Button = findViewById(R.id.btn_start)
        val et_name : EditText = findViewById(R.id.box)
        btn_start.setOnClickListener{
            if(et_name.text.isEmpty())
                Toast.makeText(this,"Please enter your name", Toast.LENGTH_LONG).show()
            else
            {
                //to move to current screen
                val intent = Intent(this,QuizQuestion::class.java)
                intent.putExtra(Constants.USER_NAME,et_name.text.toString())

                startActivity(intent)
                finish()//to dont go back to rpevious screen

            }
    }
}
}