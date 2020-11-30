package com.example.warehouselimmingjun

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.warehouselimmingjun.DBHelper.DBHelper_item

class stockOutForm : AppCompatActivity() {

    internal lateinit var dbHelper: DBHelper_item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_out_form)

        dbHelper = DBHelper_item(this)

        val shirt = intent.getStringExtra("Shirt")
        val shoes = intent.getStringExtra("Shoes")
        val sessionId = intent.getStringExtra("emailAddress")
        val sessionId1 = intent.getStringExtra("name")

        //Retrieving value from RecycleView
        val intent = intent
        val category = intent.getStringExtra("ProductCategory")
        val name = intent.getStringExtra("ProductName")
        val size = intent.getStringExtra("ProductSize")
        val qty = intent.getStringExtra("ProductQty")

        val myCategory = findViewById<TextView>(R.id.CategoryInput)
        myCategory.text = category

        val myName = findViewById<TextView>(R.id.ProductInput)
        myName.text = name

        val mySize = findViewById<TextView>(R.id.SizeInput)
        mySize.text = size

        val myQty = findViewById<TextView>(R.id.stockAvailable)
        myQty.text = "Remaining stocks: $qty"

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener {
            val intent = Intent(this, StockOutList::class.java)
            intent.putExtra("Shirt", shirt)
            intent.putExtra("Shoes", shoes)

            intent.putExtra("emailAddress", sessionId)
            intent.putExtra("name", sessionId1)
            startActivity(intent)
        }

        val plusButton = findViewById<ImageButton>(R.id.plusButton)
        plusButton.setOnClickListener {
            val myQty = findViewById<EditText>(R.id.QuantityText)

            if (myQty.text.toString().isNotEmpty()) {
                var number = myQty.text.toString().toInt()
                number++
                myQty.text = SpannableStringBuilder(number.toString())
            } else {
                val num = 1
                myQty.text = SpannableStringBuilder(num.toString())
            }
        }

        val confirmBtn1 = findViewById<ImageButton>(R.id.confirmButton)
        confirmBtn1.setOnClickListener {

            val myQty = findViewById<EditText>(R.id.QuantityText)
            val checkQty = validateQtyField(myQty)

            if (checkQty)
            {
                val productName = findViewById<TextView>(R.id.ProductInput)

                val qtyProduct = dbHelper.getQty(productName.text.toString())

                val qtyToBeReduce = findViewById<TextView>(R.id.QuantityText)
                var i = 1

                if (qtyProduct != null) {
                    if (qtyProduct.toInt() < qtyToBeReduce.text.toString().toInt()) {
                        i = 0
                        val builder = AlertDialog.Builder(this)
                        //set title for alert dialog
                        builder.setTitle("Quantity of Stock Out Cannot More Than Current Quantity Stock !!!")
                        //set message for alert dialog
                        builder.setMessage("Please Key In the Valid Amount !!!")
                        builder.setPositiveButton("OK",
                            DialogInterface.OnClickListener { dialog, id ->

                                myQty.setText("")
                            })

                        //Create the AlertDialog
                        val alertDialog: AlertDialog = builder.create()
                        //set other dialog properties
                        alertDialog.setCancelable(false)
                        alertDialog.show()
                    }
                }
                if (i == 1)
                {
                    val latestAmountQty = qtyProduct?.toInt()?.minus(qtyToBeReduce.text.toString().toInt())

                    val builder = AlertDialog.Builder(this)
                    //set title for alert dialog
                    builder.setTitle("Stock Out Confirmation")
                    //set message for alert dialog
                    builder.setMessage("Confirm Out Stock?")

                    //performing positive action
                    builder.setPositiveButton("Confirm",
                        DialogInterface.OnClickListener { dialog, id ->
                            if (latestAmountQty != null)
                            {
                                if (dbHelper.updateQty(productName.text.toString(), latestAmountQty.toInt()))
                                {
                                    Toast.makeText(this, "Successfully Shipped!", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this, HomeScreen::class.java)
                                    intent.putExtra("emailAddress", sessionId)
                                    intent.putExtra("name", sessionId1)
                                    startActivity(intent)
                                }
                            }
                        })

                    //performing negative action
                    builder.setNegativeButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            //Toast.makeText(this, "Cancel Clear!", Toast.LENGTH_SHORT).show()
                        })

                    //Create the AlertDialog
                    val alertDialog: AlertDialog = builder.create()
                    //set other dialog properties
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
            }
        }

        val clearBtn1 = findViewById<ImageButton>(R.id.clearButton)
        clearBtn1.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Clear Action Confirmation")
            //set message for alert dialog
            builder.setMessage("Sure to clear data?")
            //builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(this, "Cleared!", Toast.LENGTH_SHORT).show()
                    val myProductQty = findViewById<EditText>(R.id.QuantityText)
                    myProductQty.setText("")
                })

            //performing negative action
            builder.setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    //Toast.makeText(this, "Cancel Clear!", Toast.LENGTH_SHORT).show()
                })

            //Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            //set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private fun validateQtyField(myQty: EditText): Boolean {

        if (myQty.text.toString().isEmpty()) {
            myQty.error = "Field can't be empty!"
        }
        else if (myQty.text.toString().contains(" ")) {
            myQty.error = "No whitespace!"
        }
        else if (!(myQty.text.toString().matches("-?\\d+(\\.\\d+)?".toRegex()))) {
            myQty.error = "Only numeric characters!"
        }
        else {
            myQty.error = null
            return true
        }
        return false
    }
}