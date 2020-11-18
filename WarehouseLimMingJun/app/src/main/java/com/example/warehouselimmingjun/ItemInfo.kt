package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ItemInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val editBtn = findViewById<ImageButton>(R.id.imageButton3)
        editBtn.setOnClickListener{
            val intent = Intent(this, EditItem::class.java)
            startActivity(intent)
        }
    }
}