package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.warehouselimmingjun.HomeScreen
import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.StockInList

class stockOutForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_out_form)

        val backBtn = findViewById<ImageButton>(R.id.buttonBack5)
        backBtn.setOnClickListener{
            val intent = Intent(this, StockInList::class.java)
            startActivity(intent)
        }

        val confirmBtn1 = findViewById<ImageButton>(R.id.confirmButton)
        confirmBtn1.setOnClickListener{
            Toast.makeText(this,"Decreased!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val clearBtn1 = findViewById<ImageButton>(R.id.clearButton)
        clearBtn1.setOnClickListener{

        }
    }
}