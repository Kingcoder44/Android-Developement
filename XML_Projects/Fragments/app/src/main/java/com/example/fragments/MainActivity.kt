package com.example.fragments

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val bottomnav  :BottomNavigationView = findViewById(R.id.bottom_nav)

        bottomnav.setOnNavigationItemSelectedListener {
                val id =it.itemId
            when(id)
            {
                R.id.home->{
                    openFragment(FirstFragment())
                }
                R.id.profile->{
                    openFragment(SecondFragment())
                }R.id.cart->{
                openFragment(ThirdFragment())
            }
            }
            return@setOnNavigationItemSelectedListener true
        }

        //to have different fragments in fragment container tube
        //jumping between fragments-transation
//        btn.setOnClickListener {
//
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fcv,FirstFragment())
//                .commit()
//        }
            }private fun openFragment(fragment: Fragment)
                 {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv,fragment)
                    .commit()
                 }


}