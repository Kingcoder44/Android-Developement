package com.example.a7minutesworkout

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.a7minutesworkout.databinding.ActivityMainBinding
//we use viewbinding
class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding?.root)
      // val flStartButton :FrameLayout = findViewById(R.id.flstart)
        binding?.flstart?.setOnClickListener{
            //to move over to exercise screen
                val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }
        binding?.btnBmi ?.setOnClickListener{
            //to move over to exercise screen
            val intent = Intent(this,BMI_Activity::class.java)
            startActivity(intent)
        }
        binding?.imageButton?.setOnClickListener{
            //to move over to exercise screen
            val intent = Intent(this,History_Activity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}