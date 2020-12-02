package com.example.warehouselimmingjun

import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.warehouselimmingjun.DBHelper.DBHelper_History

class history_detail : AppCompatActivity() {

    internal lateinit var dbHelper: DBHelper_History
    private val PHOTO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_detail)
        dbHelper = DBHelper_History(this)

        val hisID = intent.getIntExtra("HistoryID", 0)
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

        val deleteBtn = findViewById<ImageButton>(R.id.deleteButton)
        deleteBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Delete Item Confirmation")
            //set message for alert dialog
            builder.setMessage("Confirm Delete Item?")

            //performing positive action
            builder.setPositiveButton("Confirm",
                DialogInterface.OnClickListener { dialog, id ->
                    if(dbHelper.deleteProduct(hisID))
                    {
                        Toast.makeText(this, "History deleted successful!", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, TransactionHistoryList::class.java)
                        intent.putExtra("emailAddress", emailAddress)
                        intent.putExtra("name", name)
                        startActivity(intent)
                    }
                }
            )

            //performing negative action
            builder.setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(this, hisID.toString(), Toast.LENGTH_SHORT).show()
                }
            )

            //Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            //set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }
}

