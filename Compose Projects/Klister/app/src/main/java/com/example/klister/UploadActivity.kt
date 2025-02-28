package com.example.klister

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class UploadActivity : AppCompatActivity() {
    private lateinit var productImage: ImageView
    private lateinit var productName: EditText
    private lateinit var productPrice: EditText
    private lateinit var productDescription: EditText
    private lateinit var selectImage: Button
    private lateinit var uploadButton: Button
    private lateinit var progressBar: ProgressBar

    private var imageUri: Uri? = null
    private lateinit var selectImageLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Initialize views
        productImage = findViewById(R.id.img)
        productName = findViewById(R.id.p_name)
        productPrice = findViewById(R.id.p_price)
        productDescription = findViewById(R.id.p_des)
        selectImage = findViewById(R.id.s_img)
        uploadButton = findViewById(R.id.upload)
        progressBar = findViewById(R.id.loader)

        // Initialize the image picker
        selectImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                imageUri = data?.data
                productImage.setImageURI(imageUri)
            }
        }

        selectImage.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            selectImageLauncher.launch(galleryIntent)
        }

        uploadButton.setOnClickListener {
            if (imageUri != null) {
                uploadImage(imageUri!!)
            } else {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadImage(uri: Uri) {
        progressBar.visibility = View.VISIBLE
        uploadButton.isEnabled = false

        val fileName = UUID.randomUUID().toString() + ".jpg"
        val storageRef = FirebaseStorage.getInstance().reference.child("product_images/$fileName")

        storageRef.putFile(uri)
            .addOnSuccessListener {
                Log.i("upload_success", "Image uploaded successfully!")
                storageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                    Log.i("download_url", downloadUrl.toString())
                    uploadProduct(downloadUrl.toString(), productName.text.toString(), productPrice.text.toString(), productDescription.text.toString())
                }.addOnFailureListener { exception ->
                    Log.e("download_url_failure", "Failed to get download URL: ${exception.message}")
                    progressBar.visibility = View.GONE
                    uploadButton.isEnabled = true
                }
            }
            .addOnFailureListener { exception ->
                Log.e("upload_failure", "Image upload failed: ${exception.message}")
                progressBar.visibility = View.GONE
                uploadButton.isEnabled = true
            }
    }

    private fun uploadProduct(imageUrl: String, name: String, price: String, description: String) {
        val database = Firebase.database
        val productRef = database.getReference("products").child(name)
        val product = Product(name, price, description, imageUrl)

        productRef.setValue(product)
            .addOnSuccessListener {
                progressBar.visibility = View.GONE
                uploadButton.isEnabled = true
                Toast.makeText(this, "Product Uploaded Successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                progressBar.visibility = View.GONE
                uploadButton.isEnabled = true
                Toast.makeText(this, "Failed to upload product: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
