package com.example.warehouselimmingjun

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.drawToBitmap
import com.example.warehouselimmingjun.DBHelper.DBHelper_item
import java.io.ByteArrayOutputStream

class EditItem : AppCompatActivity() {
    internal lateinit var dbHelper: DBHelper_item
    private val PHOTO = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)
        dbHelper = DBHelper_item(this)

        val intent = intent
        val id = intent.getStringExtra("ProductID")
        val location = intent.getStringExtra("ProductLoc")
        val name = intent.getStringExtra("ProductName")
        val qty = intent.getStringExtra("ProductQty")
        val price = intent.getStringExtra("ProductPrice")
        val category = intent.getStringExtra("ProductCategory")
        val size = intent.getStringExtra("ProductSize")
        val image = intent.getByteArrayExtra("ProductImg")
        val sessionId = intent.getStringExtra("emailAddress")
        val sessionId1 = intent.getStringExtra("name")

        val myID = findViewById<TextView>(R.id.ProductIDInput)
        myID.text = id

        val myLocation = findViewById<EditText>(R.id.LocationText)
        myLocation.setText(location)

        val myName = findViewById<EditText>(R.id.ProductNameText)
        myName.setText(name)

        val myQty = findViewById<EditText>(R.id.QuantityText)
        myQty.setText(qty)

        val myPrice = findViewById<EditText>(R.id.PriceText)
        myPrice.setText(price)

        val myCategory = findViewById<TextView>(R.id.CategoryInput)
        myCategory.text = category

        val mySize = findViewById<TextView>(R.id.SizeInput)
        mySize.text = size

        val myImg = findViewById<ImageView>(R.id.productImage)

        val options: BitmapFactory.Options? = BitmapFactory.Options()
        val bitmap = BitmapFactory.decodeByteArray(image, 0, image!!.size, options)
        myImg.setImageBitmap(bitmap)
        val bitmap2 = bitmap

        //IMAGE BUTTON FOR SELECTING IMAGE
        val pickImageButton = findViewById<ImageButton>(R.id.photo_button)
        pickImageButton.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, PHOTO)
        }

        if (Build.VERSION.SDK_INT > 25)
        {
            if (myCategory.text == "Shirt") {
                myLocation.tooltipText = "Format is LOC(Shirt location) + 001(Number) + A(Section) = LOC001A"
            }
            else{
                myLocation.tooltipText = "Format is LOT(Shoe location) + 001(Number) + A(Section) = LOT001A"
            }
        }


        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{

            val intent = Intent(this, ItemInfo::class.java)
            intent.putExtra("emailAddress", sessionId)
            intent.putExtra("name", sessionId1)
            startActivity(intent)
        }

        val confirmBtn = findViewById<ImageButton>(R.id.confirmButton)
        confirmBtn.setOnClickListener{

            val myCategory = findViewById<TextView>(R.id.CategoryInput)

            val myProductName = findViewById<EditText>(R.id.ProductNameText)
            val checkProductName = validateProductName(myProductName)
            val myID = findViewById<TextView>(R.id.ProductIDInput)
            val myProductQty = findViewById<EditText>(R.id.QuantityText)
            val checkProductQty = validateProductQty(myProductQty)

            val myProductPrice = findViewById<EditText>(R.id.PriceText)
            val checkProductPrice = validateProductPrice(myProductPrice)

            val myProductLoc = findViewById<EditText>(R.id.LocationText)
            val checkProductLoc = validateProductLoc(myProductLoc, myCategory)


            if (checkProductName && checkProductQty && checkProductPrice && checkProductLoc)
            {
                val productImage = findViewById<ImageView>(R.id.productImage)
                val myLocation = findViewById<TextView>(R.id.LocationText)
                val myName = findViewById<TextView>(R.id.ProductNameText)
                val myQty = findViewById<TextView>(R.id.QuantityText)
                val myPrice = findViewById<TextView>(R.id.PriceText)
                val bitmap = productImage.drawable.toBitmap()

                //Compress the bitmap
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                val image1 = stream.toByteArray()

                val builder = AlertDialog.Builder(this)
                //set title for alert dialog
                builder.setTitle("Edit Item Information Confirmation")
                //set message for alert dialog
                builder.setMessage("Confirm Edit Item Information?")
                //builder.setIcon(android.R.drawable.ic_dialog_alert)

                //performing positive action
                builder.setPositiveButton("Confirm",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Put your dbHelper here to edit changes
                        if(dbHelper.editQty(myID.text.toString(),myQty.text.toString()))
                        {
                            if(dbHelper.editLoc(myID.text.toString(),myLocation.text.toString()))
                            {
                                if(dbHelper.editImg(myID.text.toString(),image1))
                                {
                                    if(dbHelper.editProductName(myID.text.toString(),myName.text.toString()))
                                    {
                                        if(dbHelper.editPrice(myID.text.toString(),myPrice.text.toString()))
                                        {
                                            Toast.makeText(this, "Item edit successfully!", Toast.LENGTH_SHORT).show()
                                            val intent = Intent(this, HomeScreen::class.java)
                                            intent.putExtra("emailAddress", sessionId)
                                            intent.putExtra("name", sessionId1)
                                            startActivity(intent)
                                        }
                                    }
                                }
                            }
                        }
                    })

                //performing negative action
                builder.setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        myLocation.text = location

                        myName.text = name

                        myQty.text = qty

                        myPrice.text = price

                        myImg.setImageBitmap(bitmap2)
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
                    val myProductName = findViewById<EditText>(R.id.ProductNameText)
                    myProductName.setText("")

                    val myProductQty = findViewById<EditText>(R.id.QuantityText)
                    myProductQty.setText("")

                    val myLocation = findViewById<EditText>(R.id.LocationText)
                    myLocation.setText("")

                    val myProductPrice = findViewById<EditText>(R.id.PriceText)
                    myProductPrice.setText("")

                    val myImg = findViewById<ImageView>(R.id.productImage)
                    myImg.setImageDrawable(null)
                })

            //performing negative action
            builder.setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    //Toast.makeText(this, "Cancel Clear!", Toast.LENGTH_SHORT).show()
                })

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

    private fun validateProductLoc(myProductLoc: EditText, myCategory: TextView): Boolean {

        val myPatternShoe: Regex = Regex("^[L][O][T][0-9]{3}[A-Z]\$")
        val myPattern: Regex = Regex("^[L][O][C][0-9]{3}[A-Z]\$")
        val myPattern2: Regex = Regex("^[^A-Z0-9]+\$")

        if (myCategory.text.toString() == "Shirt")
        {
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
        else
        {
            if (myProductLoc.text.toString().isEmpty()) {
                myProductLoc.error = "Field can't be empty!"
            }
            else if (myProductLoc.text.toString().contains(" ")) {
                myProductLoc.error = "No whitespace!"
            }
            else if (myPattern2.matches(myProductLoc.text.toString())) {
                myProductLoc.error = "Only uppercase and numeric characters!"
            }
            else if (!(myPatternShoe.matches(myProductLoc.text.toString()))) {
                //myProductLoc.error = "Format: LOT + number(0-9) + section(A-Z)"
                myProductLoc.error = "Format wrong! Ex: LOT000A - LOT999Z."
            }
            else {
                myProductLoc.error = null
                return true
            }
            return false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PHOTO && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                Toast.makeText(this, "No image selected!", Toast.LENGTH_SHORT).show()
                return
            }
            val productImage = findViewById<ImageView>(R.id.productImage)
            productImage.setImageURI(data.data)
        }
    }
}