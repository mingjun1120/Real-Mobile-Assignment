package com.example.warehouselimmingjun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmingjun.adapter.HistoryAdapter
import com.example.warehouselimmingjun.model.HistoryList

class TransactionHistory : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_history)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener {
            val intent = Intent(this, HomeScreen::class.java)
            val sessionId = getIntent().getStringExtra("emailAddress")
            val sessionId1 = getIntent().getStringExtra("name")
            intent.putExtra("emailAddress", sessionId)
            intent.putExtra("name", sessionId1)
            startActivity(intent)
        }

        val historyList = listOf<HistoryList>(
            HistoryList(R.drawable.shirt, "ST0001M", "Pink TShirt","Stock In:","20","Stock Out:", "-", "1/12/2020 4:30pm"),
            HistoryList(R.drawable.shirt, "ST0001L", "Pink TShirt","Stock In:","-", "Stock Out:","30", "1/12/2020 4:35pm")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.imageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HistoryAdapter(this, historyList) {
            //val intent = Intent(this, stockOutForm::class.java)
            //startActivity(intent)
        }
    }
}