package com.example.webviewtest

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
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
        val txt : EditText = findViewById(R.id.url)
        val btn : Button = findViewById(R.id.












        btn)
      btn.setOnClickListener {
          val i = Intent(this,SecondActivity::class.java)
          i.putExtra("link",txt.text)

          startActivity(i)

      }
    }
}