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
            val sessionId = getIntent().getStringExtra("emailAddress")
            val sessionId1 = getIntent().getStringExtra("name")
            intent.putExtra("emailAddress", sessionId);
            intent.putExtra("name", sessionId1);
            startActivity(intent)
        }

        val shirtBtn = findViewById<ImageButton>(R.id.buttonShirt)
        shirtBtn.setOnClickListener{
            val intent = Intent(this, StockOutList::class.java)
            intent.putExtra("Shirt", "shirt")
            val sessionId = getIntent().getStringExtra("emailAddress")
            val sessionId1 = getIntent().getStringExtra("name")
            intent.putExtra("emailAddress", sessionId);
            intent.putExtra("name", sessionId1);
            startActivity(intent)
        }

        val shoeBtn = findViewById<ImageButton>(R.id.buttonShoe)
        shoeBtn.setOnClickListener{
            val intent = Intent(this, StockOutList::class.java)
            intent.putExtra("Shoes", "shoes")
            val sessionId = getIntent().getStringExtra("emailAddress")
            val sessionId1 = getIntent().getStringExtra("name")
            intent.putExtra("emailAddress", sessionId);
            intent.putExtra("name", sessionId1);
            startActivity(intent)
        }
    }
}