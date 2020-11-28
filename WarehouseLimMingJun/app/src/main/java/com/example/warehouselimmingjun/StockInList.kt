package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmimgjun.adapter.ItemAdapter
import com.example.warehouselimmingjun.DBHelper.DBHelper
import com.example.warehouselimmingjun.DBHelper.DBHelper_item
import com.example.warehouselimmingjun.model.Item
import com.example.warehouselimmingjun.model.ItemList

class StockInList : AppCompatActivity() {

    internal lateinit var dbHelper: DBHelper_item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_in_list)
        var shirtList = listOf<Item>()
        var shoeList = listOf<Item>()
        dbHelper = DBHelper_item(this)

        val shirt = intent.getStringExtra("Shirt")
        val shoes = intent.getStringExtra("Shoes")

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener {
            val intent = Intent(this, StockIn::class.java)
            val sessionId = getIntent().getStringExtra("emailAddress")
            val sessionId1 = getIntent().getStringExtra("name")
            intent.putExtra("emailAddress", sessionId)
            intent.putExtra("name", sessionId1)
            startActivity(intent)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.imageRecyclerView)

        if(shirt == "shirt"){
            shirtList = dbHelper.retrieveShirtItem()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ItemAdapter(this, shirtList){
                val intent = Intent(this, stockInForm::class.java)
                intent.putExtra("Shirt", "shirt")
                val sessionId = getIntent().getStringExtra("emailAddress")
                val sessionId1 = getIntent().getStringExtra("name")
                intent.putExtra("emailAddress", sessionId)
                intent.putExtra("name", sessionId1)
                startActivity(intent)
            }
        }
        else if(shoes == "shoes"){
            shoeList =  dbHelper.retrieveShoesItem()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ItemAdapter(this, shoeList){
                val intent = Intent(this, stockInForm::class.java)
                intent.putExtra("Shoes", "shoes")
                val sessionId = getIntent().getStringExtra("emailAddress")
                val sessionId1 = getIntent().getStringExtra("name")
                intent.putExtra("emailAddress", sessionId)
                intent.putExtra("name", sessionId1)
                startActivity(intent)
            }
        }
    }
}