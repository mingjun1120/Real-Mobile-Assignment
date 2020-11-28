package com.example.warehouselimmingjun

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class EditItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, ItemInfo::class.java)
            val sessionId = getIntent().getStringExtra("emailAddress")
            val sessionId1 = getIntent().getStringExtra("name")
            intent.putExtra("emailAddress", sessionId);
            intent.putExtra("name", sessionId1);
            startActivity(intent)
        }

        val confirmBtn = findViewById<ImageButton>(R.id.confirmButton)
        confirmBtn.setOnClickListener{

            val myProductName = findViewById<EditText>(R.id.ProductNameText)
            val checkProductName = validateProductName(myProductName)

            val myProductQty = findViewById<EditText>(R.id.QuantityText)
            val checkProductQty = validateProductQty(myProductQty)

            val myProductPrice = findViewById<EditText>(R.id.PriceText)
            val checkProductPrice = validateProductPrice(myProductPrice)

            // Need to check which category first only can validate(This is for validate shirt location only!)
            val myProductLoc = findViewById<EditText>(R.id.LocationText)
            val checkProductLoc = validateProductLoc(myProductLoc)

            if (checkProductName && checkProductQty && checkProductPrice && checkProductLoc)
            {
                val builder = AlertDialog.Builder(this)
                //set title for alert dialog
                builder.setTitle("Edit Item Information Confirmation")
                //set message for alert dialog
                builder.setMessage("Confirm Edit Item Information?")
                //builder.setIcon(android.R.drawable.ic_dialog_alert)

                //performing positive action
                builder.setPositiveButton("Confirm",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this,"Item edit successfully!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeScreen::class.java)
                        val sessionId = getIntent().getStringExtra("emailAddress")
                        val sessionId1 = getIntent().getStringExtra("name")
                        intent.putExtra("emailAddress", sessionId);
                        intent.putExtra("name", sessionId1);
                        startActivity(intent)
                    })

                //performing negative action
                builder.setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this, "Cancelled Edit Item Information!", Toast.LENGTH_SHORT).show()
                    });

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
                    val myProductName = findViewById<EditText>(R.id.ProductNameText)
                    myProductName.setText("")

                    val myProductQty = findViewById<EditText>(R.id.QuantityText)
                    myProductQty.setText("")

                    val myLocation = findViewById<EditText>(R.id.LocationText)
                    myLocation.setText("")

                    val myProductPrice = findViewById<EditText>(R.id.PriceText)
                    myProductPrice.setText("")
                })

            //performing negative action
            builder.setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    //Toast.makeText(this, "Cancel Clear!", Toast.LENGTH_SHORT).show()
                });

            //Create the AlertDialog
            val alertDialog:AlertDialog = builder.create()
            //Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private fun validateProductName(myProductName: EditText): Boolean {

        val myPattern: Regex = Regex("^[A-Z][A-Za-z()* ]*\$")

        if (myProductName.text.toString().isEmpty()) {
            myProductName.error = "Field can't be empty!"
        }
        else if (myProductName.text.toString().first() != myProductName.text.toString().first().toUpperCase()) {
            myProductName.error = "1st character must be uppercase!"
        }
        else if (!(myPattern.matches(myProductName.text.toString()))) {
            myProductName.error = "No special characters! Eg:!@#"
        }
        else {
            myProductName.error = null
            return true
        }
        return false
    }

    private fun validateProductQty(myProductQty: EditText): Boolean {

        if (myProductQty.text.toString().isEmpty()) {
            myProductQty.error = "Field can't be empty!"
        }
        else if (myProductQty.text.toString().contains(" ")) {
            myProductQty.error = "No whitespace!"
        }
        else if (!(myProductQty.text.toString().matches("-?\\d+(\\.\\d+)?".toRegex()))) {
            myProductQty.error = "Only numeric characters!"
        }
        else {
            myProductQty.error = null
            return true
        }
        return false
    }

    private fun validateProductPrice(myProductPrice: EditText): Boolean {

        if (myProductPrice.text.toString().isEmpty()) {
            myProductPrice.error = "Field can't be empty!"
        }
        else if (myProductPrice.text.toString().contains(" ")) {
            myProductPrice.error = "No whitespace!"
        }
        else if ((myProductPrice.text.toString().toDoubleOrNull()) == null) {
            myProductPrice.error = "Only decimal numbers!"
        }
        else {
            myProductPrice.error = null
            return true
        }
        return false
    }

    private fun validateProductLoc(myProductLoc: EditText): Boolean {

        val myPattern: Regex = Regex("^[L][O][C][0-9]{3}[A-Z]\$")
        val myPattern2: Regex = Regex("^[^A-Z0-9]+\$")

        if (myProductLoc.text.toString().isEmpty()) {
            myProductLoc.error = "Field can't be empty!"
        }
        else if (myProductLoc.text.toString().contains(" ")) {
            myProductLoc.error = "No whitespace!"
        }
        else if (myPattern2.matches(myProductLoc.text.toString())) {
            myProductLoc.error = "Only uppercase and numeric characters!"
        }
        else if (!(myPattern.matches(myProductLoc.text.toString()))) {
            //myProductLoc.error = "Format: LOC + number(0-9) + section(A-Z)"
            myProductLoc.error = "Format wrong! Ex: LOC000A - LOC999Z."
        }
        else {
            myProductLoc.error = null
            return true
        }
        return false
    }
}