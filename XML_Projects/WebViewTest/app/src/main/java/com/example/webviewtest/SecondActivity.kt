package com.example.webviewtest

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        val link = intent.getStringExtra("link")
        val wv : WebView = findViewById(R.id.webview)
        wv.webViewClient = WebViewClient()
        wv.settings.javaScriptEnabled=true
        wv.loadUrl(link!!)
    }
}