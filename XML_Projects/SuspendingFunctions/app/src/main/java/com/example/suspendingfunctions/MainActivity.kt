package com.example.suspendingfunctions

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
//suspend function can be called only in other suspend functions
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
      lifecycleScope.launch(Dispatchers.Main){
          getData()
                  }
    }
    private suspend fun getData()
    {
        delay(1500)
        Toast.makeText(this@MainActivity, "Some app launched", Toast.LENGTH_SHORT).show()
    }
    private suspend fun help()  //if we want to call a suspend function iit must be also a suspend function
    {
        getData()
    }
}