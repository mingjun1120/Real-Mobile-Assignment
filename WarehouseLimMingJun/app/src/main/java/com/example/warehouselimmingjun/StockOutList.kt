package com.example.warehouselimmingjun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmimgjun.adapter.ItemAdapter
import com.example.warehouselimmingjun.model.ItemList

class StockOutList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_out_list)

        val itemList = listOf<ItemList>(
            ItemList(R.drawable.shirt, "ST0001M", "Pink TShirt"),
            ItemList(R.drawable.shirt, "ST0001L", "Pink TShirt")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.imageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemOutAdapter(this, itemList) {
            //addBtn.setOnClickListener{
            val intent = Intent(this, stockOutForm::class.java)
            startActivity(intent)
        }
    }
}