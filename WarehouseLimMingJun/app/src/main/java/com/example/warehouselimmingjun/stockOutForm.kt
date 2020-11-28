package com.example.warehouselimmingjun

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class stockOutForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_out_form)

        val shirt = intent.getStringExtra("Shirt")
        val shoes = intent.getStringExtra("Shoes")

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, StockOutList::class.java)
            intent.putExtra("Shirt", "shirt")
            intent.putExtra("Shoes", "shoes")
            val sessionId = getIntent().getStringExtra("emailAddress")
            val sessionId1 = getIntent().getStringExtra("name")
            intent.putExtra("emailAddress", sessionId);
            intent.putExtra("name", sessionId1);
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

        val confirmBtn1 = findViewById<ImageButton>(R.id.confirmButton)
        confirmBtn1.setOnClickListener{

            val myQty = findViewById<EditText>(R.id.QuantityText)
            val checkQty = validateQtyField(myQty)

            if (checkQty)
            {
                val builder = AlertDialog.Builder(this)
                //set title for alert dialog
                builder.setTitle("Stock Out Confirmation")
                //set message for alert dialog
                builder.setMessage("Confirm Out Stock?")

                //performing positive action
                builder.setPositiveButton("Confirm",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this,"Successfully Sent Out!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeScreen::class.java)
                        startActivity(intent)
                    })

                //performing negative action
                builder.setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this, "Cancelled!", Toast.LENGTH_SHORT).show()
                    })

                //Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                //set other dialog properties
                alertDialog.setCancelable(false)
                alertDialog.show()
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