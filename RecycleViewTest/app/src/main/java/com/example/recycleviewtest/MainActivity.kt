package com.example.recycleviewtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv: RecyclerView = findViewById(R.id.rv)
        val contList = mutableListOf(
            ContactItem(
                imgres = R.drawable.ic_launcher_background,
                headtxt = "Kushagra Srivastava",
                subheadtxt = "This message is from Kushagra"
            ),
            ContactItem(
                imgres = R.drawable.photo_6080166398589514273_y,
                headtxt = "AB",
                subheadtxt = "This message is from AB"
            ),
            ContactItem(
                imgres = R.drawable.ic_launcher_foreground,
                headtxt = "CD",
                subheadtxt = "This message is from CD"
            )
        )

        // Instantiate the adapter
        val adapt = ContactListAdapter(contList)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapt
    }
}
