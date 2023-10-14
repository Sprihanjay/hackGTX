package com.example.thementai
import com.google.firebase.database.FirebaseDatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.util.Log
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia

class UploadPeople : AppCompatActivity() {
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("users")

    private lateinit var button: Button
    private lateinit var nameEditText: EditText
    private lateinit var detailEditText: EditText
    // Registers a photo picker activity launcher in multi-select mode.

// In this example, the app lets the user select up to 5 media files.
    val pickSingleMedia = registerForActivityResult(
        ActivityResultContracts.GetContent()) { uri: Uri? ->
            // Callback is invoked after the user selects media items or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_people)

        val pickPhotoButton = findViewById<Button>(R.id.choosePhotosButton)

//        // Set a click event listener for the button
        pickPhotoButton.setOnClickListener {
//                // Launch the photo picker and let the user choose a single image.
            pickSingleMedia.launch("image/*")

        button = findViewById(R.id.SaveToDatabase)
        nameEditText = findViewById(R.id.Name)
        detailEditText = findViewById(R.id.Details)
        //Check the Pic thing
        // Set a click listener for the button
        button.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val name = nameEditText.text.toString()
        val detail = detailEditText.text.toString()

        // Check if name and detail are not empty before saving
        if (name.isNotEmpty() && detail.isNotEmpty()) {
            // Generate a unique key for each user
            val userKey = myRef.push().key

            if (userKey != null) {
                // Save name and detail as separate entries
                myRef.child(userKey).child("name").setValue(name)
                myRef.child(userKey).child("detail").setValue(detail)

                // Clear the EditText fields
                nameEditText.text.clear()
                detailEditText.text.clear()
            }
        }
    }
}




