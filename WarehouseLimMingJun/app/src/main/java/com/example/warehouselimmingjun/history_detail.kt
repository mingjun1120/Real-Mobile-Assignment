package com.example.warehouselimmingjun

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.warehouselimmingjun.DBHelper.DBHelper_History
import com.example.warehouselimmingjun.DBHelper.DBHelper_item

class history_detail : AppCompatActivity() {

    internal lateinit var dbHelper: DBHelper_History
    private val PHOTO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_detail)
        dbHelper = DBHelper_History(this)

        val prodID = intent.getStringExtra("ProductID")
        val prodName = intent.getStringExtra("ProductName")
        val stockInQty = intent.getStringExtra("ProductStockInQty")
        val stockOutQty = intent.getStringExtra("ProductStockOutQty")
        val image = intent.getByteArrayExtra("ProductImage")
        val date = intent.getStringExtra("HistoryDate")
        val userName = intent.getStringExtra("UserName")

        val emailAddress = intent.getStringExtra("emailAddress")
        val name = intent.getStringExtra("name")

        val myID = findViewById<TextView>(R.id.ProductIDInput)
        myID.text = prodID

        val dateInput = findViewById<TextView>(R.id.dateTimeInput)
        dateInput.text = date

        val prodName1 = findViewById<TextView>(R.id.ProductNameText)
        prodName1.text = prodName

        val stockIn = findViewById<TextView>(R.id.StockInText)
        stockIn.text = stockInQty

        val stockOut = findViewById<TextView>(R.id.StockOutText)
        stockOut.text = stockOutQty

        val nameInput = findViewById<TextView>(R.id.UsernameInput)
        nameInput.text = userName

        val prodImage = findViewById<ImageView>(R.id.productImage)

        val options: BitmapFactory.Options? = BitmapFactory.Options()
        val bitmap = BitmapFactory.decodeByteArray(image, 0, image!!.size, options)
        prodImage.setImageBitmap(bitmap)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, TransactionHistoryList::class.java)
            intent.putExtra("emailAddress", emailAddress)
            intent.putExtra("name", name)

            startActivity(intent)
        }
    }





}