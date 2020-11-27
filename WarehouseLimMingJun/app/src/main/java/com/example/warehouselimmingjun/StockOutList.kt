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

        val shirt = getIntent().getStringExtra("Shirt")
        val shoes = getIntent().getStringExtra("Shoes")

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener {
            val intent = Intent(this, StockOut::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.imageRecyclerView)

        if(shirt == "shirt"){
            shirtList = dbHelper.retrieveShirtItem()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ItemAdapter(this, shirtList){
                val intent = Intent(this, stockOutForm::class.java)
                intent.putExtra("Shirt", "shirt")
                startActivity(intent)
            }
        }
        else if(shoes == "shoes"){
            shoeList =  dbHelper.retrieveShoesItem()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ItemAdapter(this, shoeList){
                val intent = Intent(this, stockOutForm::class.java)
                intent.putExtra("Shoes", "shoes")
                startActivity(intent)
            }
        }
    }
}