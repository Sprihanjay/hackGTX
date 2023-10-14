package com.example.thementai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


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

    @Composable
    fun ContactCard(name: String, email: String) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = name, color = Color.Black, fontSize = 18.sp)
                Text(text = email, color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}
