package com.example.thementai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import android.net.Uri
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest


class UploadPeople : AppCompatActivity() {

    // Registers a photo picker activity launcher in multi-select mode.
// In this example, the app lets the user select up to 5 media files.
    val pickMultipleMedia = registerForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(
            10
        )
    ) { uris ->
            // Callback is invoked after the user selects media items or closes the
            // photo picker.
            if (uris.isNotEmpty()) {
                Log.d("PhotoPicker", "Number of items selected: ${uris.size}")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_people)

        val pickPhotosButton = findViewById<Button>(R.id.choosePhotosButton)

//        // Set a click event listener for the button
        pickPhotosButton.setOnClickListener {
//            // Launch the multi-photo picker when the button is clicked
//            // Launch the photo picker and let the user choose only images.
            pickMultipleMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }
}