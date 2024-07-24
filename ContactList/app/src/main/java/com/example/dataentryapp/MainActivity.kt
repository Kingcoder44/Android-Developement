package com.example.dataentryapp

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
//this was in xml file and we have converted this into a MVVM architecture
class MainActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var name :EditText
    private lateinit var phn :EditText
    private lateinit var img :ImageView
    private lateinit var btn1 :Button
    private lateinit var btn2 :Button
    private  lateinit var contactAdapt : ContactAdapter
    private lateinit var dialog: Dialog
    private var selectedImageUri: Uri? = null

    private lateinit var repo : Repo
    private lateinit var viewModel : ViewModel
    private lateinit var conviewmnodelfactory : ContactViewModelFactory
   // val listOfContact = mutableListOf<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
       repo= Repo()
       //when a viewmodel has something like vm(k) then we use viewmodelfactory
       conviewmnodelfactory = ContactViewModelFactory(repo)
       viewModel = ViewModelProvider(this,conviewmnodelfactory).get(ContactViewModel::class.java)


        rv = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        fab=findViewById(R.id.fbtn)


       (viewModel as ContactViewModel).contactLiveData.observe(this){
           contactAdapt = ContactAdapter(it)
           rv.adapter = contactAdapt

       }


        fab.setOnClickListener{
//open dialoig with 2 edit text and 2 button
            showDialog()
        }
    }
    private fun showDialog()
    {
        val dialog=Dialog(this)
        //to remove default title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //to make it cancellable on cliocking outside of dialog
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_box)
        name  = dialog.findViewById(R.id.edt)
        phn  = dialog.findViewById(R.id.phn)
        img  = dialog.findViewById(R.id.imgprv)
        btn1  = dialog.findViewById(R.id.btn)
        btn2 = dialog.findViewById(R.id.btn2)
        btn1.setOnClickListener {
            val gint = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(gint,101)
        }

        btn2.setOnClickListener {
            val name2 = name.text.toString()
            val phn = phn.text.toString()
            //for image
            val contact = Contact(
                name = name2,
                number = phn,
                img = selectedImageUri
            )
         //   listOfContact.add(contact)
            (viewModel as ContactViewModel).addContact(contact)
            //to reflect changes made immediately
            contactAdapt.notifyDataSetChanged()
            println("Contact Added")
            dialog.dismiss()}
        dialog.show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101 && resultCode== RESULT_OK)
            //set image to preview imag ein dialog box
            //we dont have acces to the above view inside activity so :
        {
            selectedImageUri = data?.data
            img.visibility = View.VISIBLE
            img.setImageURI(selectedImageUri)
        }
    }
}