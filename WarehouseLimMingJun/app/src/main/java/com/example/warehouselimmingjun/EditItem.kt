package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, ItemInfo::class.java)
            startActivity(intent)
        }

        val confirmBtn = findViewById<ImageButton>(R.id.confirmButton)
        confirmBtn.setOnClickListener{
            Toast.makeText(this,"Edited!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val clearBtn = findViewById<ImageButton>(R.id.clearButton)
        clearBtn.setOnClickListener{
            val myProductName = findViewById<EditText>(R.id.ProductNameText)
            myProductName.setText("")

            val myProductQty = findViewById<EditText>(R.id.QuantityText)
            myProductQty.setText("")

            val myLocation = findViewById<EditText>(R.id.LocationText)
            myLocation.setText("")

            val myProductPrice = findViewById<EditText>(R.id.PriceText)
            myProductPrice.setText("")
        }
    }
}