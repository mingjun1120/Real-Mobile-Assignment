package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddNewItemForm : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_item_form)

//        val category =intent.getStringExtra("Category")
//        val categoryText = findViewById<TextView>(R.id.CategoryText)
//        categoryText.text = category

        // BACK BUTTON
        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, AddNewItem::class.java)
            startActivity(intent)
        }

        //SPINNER BUTTON FOR CHOOSING SIZE
        val spinner = findViewById<Spinner>(R.id.spinner1)
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.numbers, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        // CONFIRM BUTTON
        val confirmBtn = findViewById<ImageButton>(R.id.confirmButton)
        confirmBtn.setOnClickListener{

            val intent = Intent(this, HomeScreen::class.java)

            val myProductID = findViewById<EditText>(R.id.ProductIDText)
            val checkProductID = validateProductID(myProductID)

            val myProductName = findViewById<EditText>(R.id.ProductNameText)
            val checkProductName = validateProductName(myProductName)

            val myProductQty = findViewById<EditText>(R.id.QuantityText)
            val checkProductQty = validateProductQty(myProductQty)

            val myProductPrice = findViewById<EditText>(R.id.PriceText)
            val checkProductPrice = validateProductPrice(myProductPrice)

            val myProductLoc = findViewById<EditText>(R.id.LocationText)
            val checkProductLoc = validateProductLoc(myProductLoc)

            if(checkProductID && checkProductName && checkProductQty && checkProductPrice && checkProductLoc)
            {
                startActivity(intent)
                Toast.makeText(this, "Added!", Toast.LENGTH_SHORT).show()
            }
        }

        val clearBtn = findViewById<ImageButton>(R.id.clearButton)
        clearBtn.setOnClickListener{
            val myProductID = findViewById<EditText>(R.id.ProductIDText)
            myProductID.setText("")

            val myProductName = findViewById<EditText>(R.id.ProductNameText)
            myProductName.setText("")

            val myProductQty = findViewById<EditText>(R.id.QuantityText)
            myProductQty.setText("")

            val myLocation = findViewById<EditText>(R.id.LocationText)
            myLocation.setText("")

            val myProductPrice = findViewById<EditText>(R.id.PriceText)
            myProductPrice.setText("")

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun validateProductID(myProductID: EditText): Boolean {

        val myPattern: Regex = Regex("^[S][T][0-9]{4}(S|M|L|XL)\$")
        val myPattern2: Regex = Regex("^[^A-Z0-9]+\$")

        if (myProductID.text.toString().isEmpty()) {
            myProductID.error = "Field can't be empty!"
        }
        else if (myProductID.text.toString().contains(" ")) {
            myProductID.error = "No whitespace!"
        }
        else if (myPattern2.matches(myProductID.text.toString())) {
            myProductID.error = "Only uppercase and numeric characters!"
        }
        else if (!(myPattern.matches(myProductID.text.toString()))) {
            myProductID.error = "Format wrong! Ex: ST0001(Size) = ST0001M"
        }
        else {
            myProductID.error = null
            return true
        }
        return false
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {
        val text = parent!!.getItemAtPosition(position).toString()
        Toast.makeText(this@AddNewItemForm, text, Toast.LENGTH_SHORT).show()
        //val bb = parent.getItemAtPosition(position).toString()
        //Toast.makeText(parent.context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}