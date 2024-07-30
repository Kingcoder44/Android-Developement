package com.example.klister

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var emai: EditText
    private lateinit var pass: EditText
    private lateinit var lgin: Button
    private lateinit var sgnup: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        emai = findViewById(R.id.email)
        pass = findViewById(R.id.pass)
        lgin = findViewById(R.id.loginButton)
        sgnup = findViewById(R.id.sgnup)

        auth = FirebaseAuth.getInstance()

        lgin.setOnClickListener {
            val emai = emai.text.toString().trim()
            val password = pass.text.toString().trim()
            if (validateInputs(emai, password)) {
                login(emai, password)
            }
        }

        sgnup.setOnClickListener {
            val emai = emai.text.toString().trim()
            val password = pass.text.toString().trim()
            if (validateInputs(emai, password)) {
                signup(emai, password)
            }
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
                false
            }
            password.isEmpty() -> {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Clear input fields and open UploadActivity
                    emai.setText("")
                    pass.setText("")
                    startActivity(Intent(this, UploadActivity::class.java))
                } else {
                    // Show specific error message
                    val errorMsg = task.exception?.localizedMessage ?: "Login failed"
                    Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signup(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Clear input fields and open UploadActivity

                    pass.setText("")
                    startActivity(Intent(this, UploadActivity::class.java))
                } else {
                    // Show specific error message
                    val errorMsg = task.exception?.localizedMessage ?: "Signup failed"
                    Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
                }
            }
    }
}
