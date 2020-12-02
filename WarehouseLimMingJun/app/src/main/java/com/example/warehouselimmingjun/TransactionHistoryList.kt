package com.example.warehouselimmingjun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmingjun.DBHelper.DBHelper_History
import com.example.warehouselimmingjun.adapter.HistoryAdapter
import com.example.warehouselimmingjun.model.History

class TransactionHistoryList : AppCompatActivity(), AdapterView.OnItemSelectedListener {
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

        val recyclerView = findViewById<RecyclerView>(R.id.imageRecyclerView)
        val spinner = findViewById<Spinner>(R.id.categoryspinner)
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.histotyType, R.layout.custom_spinner
        )
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        val sessionId = intent.getStringExtra("emailAddress")
        val sessionId1 = intent.getStringExtra("name")

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                val selectedItem = parent!!.getItemAtPosition(position).toString()
                if (selectedItem == "Stock In")
                {
                    historyList = dbHelper.retrieveStockInItem()
                    recyclerView.layoutManager = LinearLayoutManager(parent.context)
                    recyclerView.adapter = HistoryAdapter(parent.context, historyList,sessionId,sessionId1) {

                    }
                }
                else if (selectedItem == "Stock Out")
                {
                    historyList = dbHelper.retrieveStockOutItem()
                    recyclerView.layoutManager = LinearLayoutManager(parent.context)
                    recyclerView.adapter = HistoryAdapter(parent.context, historyList,sessionId,sessionId1) {

                    }
                }
                else
                {
                    historyList = dbHelper.retrieveAllItem()
                    recyclerView.layoutManager = LinearLayoutManager(parent.context)
                    recyclerView.adapter = HistoryAdapter(parent.context, historyList,sessionId,sessionId1) {

                    }
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}
