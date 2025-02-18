package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        val tvName : TextView= findViewById(R.id.tv_name)
        val tvScore : TextView = findViewById(R.id.tv_score)
        val btn : Button = findViewById(R.id.btn_end)

        tvName.text = intent.getStringExtra(Constants.USER_NAME)
        val total  = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val corrAns = intent.getIntExtra(Constants.CORCT_ANS,0)

        tvScore?.text = "Your score is $corrAns out of $total"
        btn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}