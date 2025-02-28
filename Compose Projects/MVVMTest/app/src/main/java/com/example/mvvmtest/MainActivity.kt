package com.example.mvvmtest

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var booksViewModel : BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        booksViewModel = ViewModelProvider(this).get(BookViewModel :: class.java)
        booksViewModel.blivedata.observe(this){
    //            use this to inflate the ui
    //            use this list and pass to adapter
            Log.i("All Books",it.toString())
        }
    }
}