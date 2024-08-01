package com.example.coroutinetest

import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var btn_show : Button
    private lateinit var btn_start : Button
    private lateinit var count : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
      btn_start=findViewById(R.id.btn_start)
        btn_show=findViewById(R.id.btn_show)
        count=findViewById(R.id.txt_count)
        btn_show.setOnClickListener{
            Toast.makeText(this, "Button is clicked", Toast.LENGTH_SHORT).show()
        }
        btn_start.setOnClickListener {
            //if we start a long task the UI gets blocked and we cannot use other features as in this case we cannot use
            //show toast button
            //for example
          lifecycleScope.launch(Dispatchers.IO) {
                  for (i in 0..10000000)
                  {
                      withContext(Dispatchers.Main){ //we use this switch context of thread that is result in backgrpound thread in main thread
                  count.text = i.toString()
                  }
              }

          }
        }
    }
}