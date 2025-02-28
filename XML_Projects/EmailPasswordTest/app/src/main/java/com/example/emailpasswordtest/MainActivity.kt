package com.example.emailpasswordtest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
  private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var lgin : Button
    private lateinit var sgnup : Button
    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        email = findViewById(R.id.email)
        password = findViewById(R.id.psswd)
        lgin = findViewById(R.id.lgin)
        sgnup = findViewById(R.id.sgnup)

        mAuth = Firebase.auth

        lgin.setOnClickListener {
            val edt = email.text.toString()
            val psw = password.text.toString()
            login(edt,psw)
        }
        sgnup.setOnClickListener {
            val edt = email.text.toString()
            val psw = password.text.toString()
            signup(edt,psw)
        }
    }

    private fun login(email:String,passw : String)
    {
//copy from assistant
        mAuth.signInWithEmailAndPassword(email,passw)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this,HomeView::class.java))
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun signup(email:String,passw : String)
    {
//copy from assistant
        mAuth.createUserWithEmailAndPassword(email,passw)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }
}