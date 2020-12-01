package com.example.warehouselimmingjun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmingjun.DBHelper.DBHelper_History
import com.example.warehouselimmingjun.adapter.HistoryAdapter
import com.example.warehouselimmingjun.model.History

class TransactionHistoryList : AppCompatActivity() {
    internal lateinit var dbHelper: DBHelper_History

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_history)
        dbHelper = DBHelper_History(this)
        var historyList = listOf<History>()

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener {
            val intent = Intent(this, HomeScreen::class.java)
            val sessionId = getIntent().getStringExtra("emailAddress")
            val sessionId1 = getIntent().getStringExtra("name")
            intent.putExtra("emailAddress", sessionId)
            intent.putExtra("name", sessionId1)
            startActivity(intent)
        }
        val sessionId = intent.getStringExtra("emailAddress")
        val sessionId1 = intent.getStringExtra("name")
        historyList = dbHelper.retrieveAllItem()
        val recyclerView = findViewById<RecyclerView>(R.id.imageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HistoryAdapter(this, historyList,sessionId,sessionId1) {
        }


    }
}
