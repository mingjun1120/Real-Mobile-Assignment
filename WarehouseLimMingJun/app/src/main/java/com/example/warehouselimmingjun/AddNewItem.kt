package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class AddNewItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_item)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val shirtBtn = findViewById<ImageButton>(R.id.imageButton)
        shirtBtn.setOnClickListener{
            val intent = Intent(this, AddNewItemForm::class.java)
//            intent.putExtra("Category", "Shirt")
            startActivity(intent)
        }

        val shoeBtn = findViewById<ImageButton>(R.id.imageButton2)
        shoeBtn.setOnClickListener{
            val intent = Intent(this, AddNewItemFormShoe::class.java)
//            intent.putExtra("Category", "Shoe")
            startActivity(intent)
        }
    }
}