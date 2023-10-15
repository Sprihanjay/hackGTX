package com.example.thementai

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt



class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 22
    private lateinit var imageView: ImageView

    private val CAMERA_PERMISSION_REQUEST = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // Lets the user authenticate using either a Class 3 biometric or
//        // their lock screen credential (PIN, pattern, or password).
//        val promptInfo = BiometricPrompt.PromptInfo.Builder().apply {
//            setTitle("Biometric login for my app")
//            setSubtitle("Log in using your biometric credential")
//            setNegativeButtonText("Cancel") // Set negative button text here
//            setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
//        }.build()
//
//
//        val biometricManager = BiometricManager.from(this)
//        when (biometricManager.canAuthenticate()) {
//            BiometricManager.BIOMETRIC_SUCCESS ->
//                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
//            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
//                Log.e("MY_APP_TAG", "No biometric features available on this device.")
//            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
//                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
//            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
//                // Prompts the user to create credentials that your app accepts.
//                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
//                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
//                        BIOMETRIC_STRONG)
//                }
//                startActivityForResult(enrollIntent, REQUEST_CODE)
//            }
//        }

        //for contact list page
        val button = findViewById<Button>(R.id.buttonAddPeople)
        button.setOnClickListener {
            openContactList()
        }

        val btnpicture = findViewById<Button>(R.id.TakePicture)
        imageView = findViewById<ImageView>(R.id.camera)

        btnpicture.setOnClickListener { view ->
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST)
            } else {
                startCamera()
            }
        }
    }



    //second page method call
    fun openContactList() {
        val intent = Intent(this, ContactList::class.java)
        startActivity(intent)
    }

    private fun startCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val photo = data?.extras?.get("data") as Bitmap?
            imageView.setImageBitmap(photo)
            // Assuming you have references to TextView2 and TextView3
            val textView2 = findViewById<TextView>(R.id.textView2)
            val textView3 = findViewById<TextView>(R.id.textView3)

            textView2.text = "The college student who stayed awake for 48 hrs."
            textView3.text = "Luke"

        } else {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == CAMERA_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }
}
