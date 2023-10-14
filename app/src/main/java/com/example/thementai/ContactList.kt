package com.example.thementai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ContactList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        val button = findViewById<Button>(R.id.buttonAddNew)
        button.setOnClickListener {
            openUploadPeople()
        }
    }

    fun openUploadPeople() {
        val intent = Intent(this, UploadPeople::class.java)
        startActivity(intent)
    }
}
