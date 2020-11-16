package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddNewItemForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_item_form)

        val backBtn = findViewById<ImageButton>(R.id.buttonBack8)
        backBtn.setOnClickListener{
            val intent = Intent(this, AddNewItem::class.java)
            startActivity(intent)
        }

        val confirmBtn = findViewById<ImageButton>(R.id.confirmButton)
        confirmBtn.setOnClickListener{
            Toast.makeText(this,"Added!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val clearBtn = findViewById<ImageButton>(R.id.clearButton)
        clearBtn.setOnClickListener{

        }
    }
}