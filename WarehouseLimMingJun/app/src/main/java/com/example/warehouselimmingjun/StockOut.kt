package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.warehouselimmingjun.HomeScreen
import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.StockInList
import com.example.warehouselimmingjun.stockOutForm

class StockOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_out)

        val backBtn = findViewById<ImageButton>(R.id.buttonBack4)
        backBtn.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val shirtBtn = findViewById<ImageButton>(R.id.buttonShirt)
        shirtBtn.setOnClickListener{
            val intent = Intent(this, StockInList::class.java)
            startActivity(intent)
        }

        val shoeBtn = findViewById<ImageButton>(R.id.buttonShoe)
        shoeBtn.setOnClickListener{
            val intent = Intent(this, stockOutForm::class.java)
            startActivity(intent)
        }
    }
}