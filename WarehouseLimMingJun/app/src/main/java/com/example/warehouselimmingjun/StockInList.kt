package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmimgjun.adapter.ItemAdapter
import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.StockIn
import com.example.warehouselimmingjun.data.Datasource

class StockInList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_in_list)

        val backBtn = findViewById<ImageButton>(R.id.buttonBack3)
        backBtn.setOnClickListener{
            val intent = Intent(this, StockIn::class.java)
            startActivity(intent)
        }

        // Initialize data.
        val myDataset = Datasource().loadProducts()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(this, myDataset)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)

    }
}