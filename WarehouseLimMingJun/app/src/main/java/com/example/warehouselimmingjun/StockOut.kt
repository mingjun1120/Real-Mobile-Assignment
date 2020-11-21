package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class StockOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_out)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val shirtBtn = findViewById<ImageButton>(R.id.buttonShirt)
        shirtBtn.setOnClickListener{
            val intent = Intent(this, StockOutList::class.java)
            startActivity(intent)
        }

        val shoeBtn = findViewById<ImageButton>(R.id.buttonShoe)
        shoeBtn.setOnClickListener{
            val intent = Intent(this, StockOutList::class.java)
            startActivity(intent)
        }
    }
}