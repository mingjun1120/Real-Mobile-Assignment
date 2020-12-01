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
import com.example.warehouselimmingjun.DBHelper.DBHelper
import com.example.warehouselimmingjun.DBHelper.DBHelper_History
import com.example.warehouselimmingjun.DBHelper.DBHelper_item
import java.text.SimpleDateFormat
import java.util.*

class stockInForm : AppCompatActivity() {

    internal lateinit var dbHelper: DBHelper_item
    internal lateinit var dbHelperHistory: DBHelper_History

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_in_form)

        dbHelper = DBHelper_item(this)
        dbHelperHistory = DBHelper_History(this)

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


        //BACK BUTTON
        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, StockInList::class.java)

            intent.putExtra("Shirt", shirt)
            intent.putExtra("Shoes", shoes)
            intent.putExtra("emailAddress", sessionId)
            intent.putExtra("name", sessionId1)
            startActivity(intent)
        }

        val plusButton = findViewById<ImageButton>(R.id.plusButton)
        plusButton.setOnClickListener {
            val myQty = findViewById<EditText>(R.id.QuantityText)

            if (myQty.text.toString().isNotEmpty())
            {
                var number = myQty.text.toString().toInt()
                number++
                myQty.text = SpannableStringBuilder(number.toString())
            }
            else{
                val num = 1
                myQty.text = SpannableStringBuilder(num.toString())
            }
        }

        val confirmBtn = findViewById<ImageButton>(R.id.confirmButton)
        confirmBtn.setOnClickListener{

            val myQty = findViewById<EditText>(R.id.QuantityText)
            val checkQty = validateQtyField(myQty)

            if (checkQty)
            {
                val builder = AlertDialog.Builder(this)
                //set title for alert dialog
                builder.setTitle("Stock In Confirmation")
                //set message for alert dialog
                builder.setMessage("Confirm Add Stock?")
                val productName = findViewById<TextView>(R.id.ProductInput)
                val qtyProduct = dbHelper.getQty(productName.text.toString())

                val qtyToBeAdd = findViewById<TextView>(R.id.QuantityText)

                val latestAmountQty = qtyProduct?.toInt()?.plus(qtyToBeAdd.text.toString().toInt())

                //performing positive action
                val image =  dbHelper.retrieveimage(productName.text.toString())
                val prodID = dbHelper.retriveProductID(productName.text.toString())
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())

                        builder.setPositiveButton("Confirm",
                            DialogInterface.OnClickListener { dialog, id ->
                                if (latestAmountQty != null)
                                {
                                    if(dbHelper.updateQty(productName.text.toString(),latestAmountQty.toInt()))
                                    {
                                        if (image != null) {
                                            dbHelperHistory.addHistory(currentDate.toString(),prodID.toString(),productName.text.toString(),qtyToBeAdd.text.toString(),"0",image)
                                        }
                                        Toast.makeText(this, "Successfully Added!", Toast.LENGTH_SHORT).show()
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

        val clearBtn = findViewById<ImageButton>(R.id.clearButton)
        clearBtn.setOnClickListener{
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
            val alertDialog:AlertDialog = builder.create()
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