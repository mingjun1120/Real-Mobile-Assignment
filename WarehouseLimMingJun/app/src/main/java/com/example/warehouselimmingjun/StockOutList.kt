package com.example.warehouselimmingjun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmimgjun.adapter.ItemAdapter
import com.example.warehouselimmingjun.DBHelper.DBHelper_item
import com.example.warehouselimmingjun.adapter.ItemOutAdapter
import com.example.warehouselimmingjun.model.Item
import com.example.warehouselimmingjun.model.ItemList

class StockOutList : AppCompatActivity() {

    internal lateinit var dbHelper: DBHelper_item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_out_list)
        var shirtList = listOf<Item>()
        var shoeList = listOf<Item>()
        dbHelper = DBHelper_item(this)

        val shirt = intent.getStringExtra("Shirt")
        val shoes = intent.getStringExtra("Shoes")

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener {
            val intent = Intent(this, StockOut::class.java)
            val sessionId = getIntent().getStringExtra("emailAddress")
            val sessionId1 = getIntent().getStringExtra("name")
            intent.putExtra("emailAddress", sessionId)
            intent.putExtra("name", sessionId1)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.imageRecyclerView)

        val sessionId = intent.getStringExtra("emailAddress")
        val sessionId1 = intent.getStringExtra("name")

        if(shirt == "shirt"){
            shirtList = dbHelper.retrieveShirtItem()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ItemOutAdapter(this, shirtList, sessionId, sessionId1, shirt){
                //val intent = Intent(this, stockOutForm::class.java)
                intent.putExtra("Shirt", "shirt")
                //val sessionId = intent.getStringExtra("emailAddress")
                //val sessionId1 = intent.getStringExtra("name")
                //intent.putExtra("emailAddress", sessionId)
                //intent.putExtra("name", sessionId1)
                //startActivity(intent)
            }
        }
        else if(shoes == "shoes"){
            shoeList =  dbHelper.retrieveShoesItem()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ItemOutAdapter(this, shoeList, sessionId, sessionId1, shoes){
                //val intent = Intent(this, stockOutForm::class.java)
                intent.putExtra("Shoes", "shoes")
                //val sessionId = intent.getStringExtra("emailAddress")
                //val sessionId1 = intent.getStringExtra("name")
                //intent.putExtra("emailAddress", sessionId)
                //intent.putExtra("name", sessionId1)
                //startActivity(intent)
            }
        }
    }
}