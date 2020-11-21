package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmimgjun.adapter.ItemAdapter
import com.example.warehouselimmingjun.model.ItemList

class StockInList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_in_list)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener {
            val intent = Intent(this, StockIn::class.java)
            startActivity(intent)
        }

        val itemList = listOf<ItemList>(
            ItemList(R.drawable.shirt, "ST0001M", "Pink TShirt"),
            ItemList(R.drawable.shirt, "ST0001L", "Pink TShirt")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.imageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemAdapter(this, itemList) {
            //addBtn.setOnClickListener{
            val intent = Intent(this, stockInForm::class.java)
            startActivity(intent)
        }
    }
}