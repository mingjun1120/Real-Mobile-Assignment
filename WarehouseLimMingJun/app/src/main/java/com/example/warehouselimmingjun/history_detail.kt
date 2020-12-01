package com.example.warehouselimmingjun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.warehouselimmingjun.DBHelper.DBHelper_item

class history_detail : AppCompatActivity() {

    internal lateinit var dbHelper: DBHelper_item
    private val PHOTO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_detail)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, TransactionHistoryList::class.java)
            //intent.putExtra("emailAddress", sessionId)
            //intent.putExtra("name", sessionId1)
            startActivity(intent)
        }
    }





}