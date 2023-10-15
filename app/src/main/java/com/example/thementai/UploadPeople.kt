package com.example.thementai

import android.net.Uri  // Required for handling URIs
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import android.content.Intent
import android.app.Activity
import android.content.ActivityNotFoundException

class UploadPeople : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("users")
    private var key = myRef.push().key

    private lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference

    private lateinit var button: Button
    private lateinit var nameEditText: EditText
    private lateinit var detailEditText: EditText
    private lateinit var Picture: ImageView
    private lateinit var PostImg: Button

    // Registers a photo picker activity launcher in multi-select mode.
    private lateinit var pickImageLauncher: ActivityResultLauncher<Uri>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_people)

        button = findViewById(R.id.SaveToDatabase)
        nameEditText = findViewById(R.id.Name)
        detailEditText = findViewById(R.id.Details)
        Picture = findViewById(R.id.Picture)
        PostImg = findViewById(R.id.PostImg)

        // Set a click listener for the "Save to Database" button
        button.setOnClickListener {
            saveData()
            key = myRef.push().key
        }
        fun pickImageFromGallery() {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            try {
                startActivityForResult(intent, PICK_IMAGE_REQUEST)
            } catch (e: ActivityNotFoundException) {
                // Handle the exception if no activity can handle the intent (e.g., no gallery app)
            }
        }
        // Set a click listener for the "Post Image" button
        PostImg.setOnClickListener {
            pickImageFromGallery()
        }


    }

    private fun saveData() {
        val name = nameEditText.text.toString()
        val detail = detailEditText.text.toString()
        val userKey = key

        // Check if name and detail are not empty before saving
        if (name.isNotEmpty() && detail.isNotEmpty()) {
            // Save name and detail as separate entries
            if (userKey != null) {
                myRef.child(userKey).child("name").setValue(name)
            }
            if (userKey != null) {
                myRef.child(userKey).child("detail").setValue(detail)
            }

            // Clear the EditText fields
            nameEditText.text.clear()
            detailEditText.text.clear()
            Picture.setImageURI(null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            if (selectedImageUri != null) {
                //display picture
                Picture.setImageURI(selectedImageUri)
                // Upload the selected image to Firebase Storage
                val userKey = key

                if (userKey != null) {
                    val storage = FirebaseStorage.getInstance()
                    val storageRef = storage.reference
                    val imagesRef = storageRef.child("images/$userKey.jpg")
                    val uploadTask = imagesRef.putFile(selectedImageUri)

                    uploadTask.addOnSuccessListener {
                        // Get the download URL for the uploaded image
                        imagesRef.downloadUrl.addOnSuccessListener { uri ->
                            // Save the download URL in the database and call saveData
                            myRef.child(userKey).child("imageURL").setValue(uri.toString())
                            saveData()
                        }
                    }
                }
            }
        }
    }

}

