package com.example.klister

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var proAdapter: ProductAdapter
    private lateinit var fab: FloatingActionButton
    private val listOfPro = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv)
        fab = findViewById(R.id.fab)

        // Initialize Firebase reference
        val databaseReference = FirebaseDatabase.getInstance().getReference("products")

        // Listener for Firebase data changes
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listOfPro.clear()
                for (dataSnapshot in snapshot.children) {
                    val pro = dataSnapshot.getValue(Product::class.java)
                    pro?.let { listOfPro.add(it) }
                }
                // Set up adapter and layout manager
                proAdapter = ProductAdapter(this@MainActivity, listOfPro)
                rv.adapter = proAdapter
                rv.layoutManager = GridLayoutManager(this@MainActivity, 2)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Failed to load products: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })

        // Example products (consider removing these if not needed)
        val p1 = Product("Iphone_14", "Rs 79000", "Brand New iPhone", "")
        val p2 = Product("Iphone_14", "Rs 79000", "Brand New iPhone", "")
        val p3 = Product("Iphone_14", "Rs 79000", "Brand New iPhone", "")
        listOfPro.add(p1)
        listOfPro.add(p2)
        listOfPro.add(p3)
        listOfPro.add(p3)

        // Floating action button click listener
        fab.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
