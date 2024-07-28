package com.example.phonenumberauth

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.auth.auth
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var phn : EditText
    private lateinit var edtotp : EditText
    private lateinit var otp : Button
    private lateinit var veri : Button
    private lateinit var mAuth : FirebaseAuth
    var verificationId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        phn = findViewById(R.id.phn)
        edtotp = findViewById(R.id.edtotp)
        otp = findViewById(R.id.otp)
        veri = findViewById(R.id.verify)

        mAuth = Firebase.auth

        otp.setOnClickListener {
            val number = "+91${phn.text}"
            //send verifivation code to number
            verifi(number)
        }


    }
    private fun verifi(num : String)
    {
        val options = PhoneAuthOptions.newBuilder(mAuth)//to pass firebase auth instantce
            .setPhoneNumber(num)//to set phone number
            .setTimeout(60L,TimeUnit.SECONDS)//timeout time i.e. wait after sending otp
            .setActivity(this) //to setActivity
            .setCallbacks(verificationCallback) //we creatre callback function and then pass it to this
            .build() //to build the otp format

        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    //we create a callback
    val verificationCallback : OnVerificationStateChangedCallbacks = object : OnVerificationStateChangedCallbacks()
    {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {

        }

        override fun onVerificationFailed(p0: FirebaseException) {

        }

        override fun onCodeSent(s: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(s, p1)
            verificationId = s
        }

    }

}