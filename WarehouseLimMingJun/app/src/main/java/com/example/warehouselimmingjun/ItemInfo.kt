package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.warehouselimmingjun.EditItem
import com.example.warehouselimmingjun.HomeScreen
import com.example.warehouselimmingjun.R

class ItemInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)

        val backBtn = findViewById<ImageButton>(R.id.buttonBack)
        backBtn.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val editBtn = findViewById<Button>(R.id.button)
        editBtn.setOnClickListener{
            val intent = Intent(this, EditItem::class.java)
            startActivity(intent)
        }
    }
}