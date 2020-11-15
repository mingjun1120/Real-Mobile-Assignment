package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.warehouselimmingjun.HomeScreen
import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.StockInList

class stockInForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_in_form)

        val backBtn = findViewById<ImageButton>(R.id.buttonBack5)
        backBtn.setOnClickListener{
            val intent = Intent(this, StockInList::class.java)
            startActivity(intent)
        }

        val confirmBtn = findViewById<ImageButton>(R.id.confirmButton)
        confirmBtn.setOnClickListener{
            Toast.makeText(this,"Increased!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val clearBtn = findViewById<ImageButton>(R.id.clearButton)
        clearBtn.setOnClickListener{

        }
    }
}