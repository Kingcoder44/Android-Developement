package com.example.listview

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val a = mutableListOf<String>("a","b","c","d","e")
        val adap = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,a)
    val lv : ListView = findViewById(R.id.lv)
        val edt: EditText = findViewById(R.id.edt)
        val btn: Button = findViewById(R.id.btn)
        btn.setOnClickListener {
            val str = edt.text.toString()
            a.add(str)
            //to notifya bout change in list to adapter we use notify
            adap.notifyDataSetChanged()
        }


        lv.adapter = adap
        lv.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //to remove an item on being clicked
                a.removeAt(position)
                adap.notifyDataSetChanged()

              //  val nameclicked = a[position]
             //to display name on being clicked
            //   Toast.makeText(this@MainActivity, nameclicked, Toast.LENGTH_SHORT).show()
            }
        })
           }
}