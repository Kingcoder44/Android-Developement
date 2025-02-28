package com.example.sharingmediaapp

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var txt :ImageView //global variable just defining not iniyializing using lateinti var
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btn : Button = findViewById(R.id.btn)
        val txt : ImageView = findViewById(R.id.pic)
        btn.setOnClickListener {
            //open gallery and select an image
            //we use action pick xcoz we wan to choose an image form gallery
            //we then type of appliction desintation by mediastore
            //as it is external we define external_cointent_uri
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
           //we will not use startActivity(gallery)
            startActivityForResult(gallery,101)//we use this because we get some datat in return
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==101 && resultCode== RESULT_OK)
        {
            txt.setImageURI(data?.data)
        }

    }
}