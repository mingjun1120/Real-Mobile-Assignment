package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class stockInForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_in_form)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, StockInList::class.java)
            startActivity(intent)
        }

        val confirmBtn = findViewById<ImageButton>(R.id.confirmButton)
        confirmBtn.setOnClickListener{

            val intent = Intent(this, HomeScreen::class.java)

            val myQty = findViewById<EditText>(R.id.QuantityText)
            val checkQty = validateQtyField(myQty)

            if (checkQty)
            {
                Toast.makeText(this,"Stock added successfully!", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }

        val clearBtn = findViewById<ImageButton>(R.id.clearButton)
        clearBtn.setOnClickListener{

        }
    }

    private fun validateQtyField(myQty: EditText): Boolean {

        if (myQty.text.toString().isEmpty()) {
            myQty.error = "Field can't be empty!"
        }
        else if (myQty.text.toString().contains(" ")) {
            myQty.error = "No whitespace!"
        }
        else if (!(myQty.text.toString().matches("-?\\d+(\\.\\d+)?".toRegex()))) {
            myQty.error = "Only numeric characters!"
        }
        else {
            myQty.error = null
            return true
        }
        return false
    }
}