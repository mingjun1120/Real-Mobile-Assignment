package com.example.warehouselimmingjun

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.toBitmap
import com.example.warehouselimmingjun.DBHelper.DBHelper_item
import java.io.ByteArrayOutputStream

class AddNewItemFormShoe : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    internal lateinit var dbHelper: DBHelper_item
    private val PHOTO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_item_form_shoe)
        dbHelper = DBHelper_item(this)
        if (Build.VERSION.SDK_INT > 25)
        {
            val myProductID = findViewById<EditText>(R.id.ProductIDText)
            myProductID.tooltipText = "Format is SH(Shoe) + 0001(Number) = ST0001"

            val myProductLoc = findViewById<EditText>(R.id.LocationText)
            myProductLoc.tooltipText = "Format is LOT(Shoe location) + 001(Number) + A(Section) = LOT001A"
        }

        // BACK BUTTON
        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, AddNewItem::class.java)
            startActivity(intent)
        }

        // SET PREFIX CONSTANT FOR PRODUCT ID
        val myProductID = findViewById<EditText>(R.id.ProductIDText)
        AddConstantTextInEditText(myProductID, "SH")

        // SET PREFIX CONSTANT FOR PRODUCT LOCATION
        val myProductLoc = findViewById<EditText>(R.id.LocationText)
        AddConstantTextInEditText(myProductLoc, "LOT")

        //SPINNER BUTTON FOR CHOOSING SIZE
        val spinner = findViewById<Spinner>(R.id.spinner1)
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.shoeNumbers, R.layout.custom_spinner
        )
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        //IMAGE BUTTON FOR SELECTING IMAGE
        val image = findViewById<ImageView>(R.id.productImage) // Image to be selected
        val pickImageButton = findViewById<ImageButton>(R.id.photo_button)
        pickImageButton.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, PHOTO)
        }

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

            val myProductCategory = findViewById<TextView>(R.id.shoeCategory)

            val myProductLoc = findViewById<EditText>(R.id.LocationText)

            var myProductSize: Spinner = findViewById<Spinner>(R.id.spinner1)

            val checkProductLoc = validateProductLoc(myProductLoc)

            val productImage = findViewById<ImageView>(R.id.productImage)
            val checkProductImg = validateProductImg(productImage)

            val bitmap = productImage.drawable.toBitmap()
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val image1 = stream.toByteArray()

            if(checkProductID && checkProductName && checkProductQty && checkProductPrice && checkProductLoc && checkProductImg)
            {
                val builder = AlertDialog.Builder(this)
                //set title for alert dialog
                builder.setTitle("Add New Item Confirmation")
                //set message for alert dialog
                builder.setMessage("Confirm Add New Item?")
                //builder.setIcon(android.R.drawable.ic_dialog_alert)

                //performing positive action
                builder.setPositiveButton("Confirm",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this,"New item added successfully!", Toast.LENGTH_SHORT).show()
                        dbHelper.addItem(myProductID.text.toString(), myProductName.text.toString(),myProductQty.text.toString(), myProductCategory.text.toString(),
                            myProductPrice.text.toString(),
                            myProductSize.selectedItem.toString(),
                            myProductLoc.text.toString(),
                            image1)
                        val intent = Intent(this, HomeScreen::class.java)
                        startActivity(intent)
                    })

                //performing negative action
                builder.setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this, "Cancelled Add New Item!", Toast.LENGTH_SHORT).show()
                    });

                //Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                //set other dialog properties
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }

        //CLEAR BUTTON
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

                    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter

                    image.setImageDrawable(null)
                })

            //performing negative action
            builder.setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    //Toast.makeText(this, "Cancel Clear!", Toast.LENGTH_SHORT).show()
                });

            //Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            //set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private fun validateProductID(myProductID: EditText): Boolean {

        //val myPattern: Regex = Regex("^[S][H][0-9]{4}[0-9]{1,2}[P]?\$")
        val myPattern: Regex = Regex("^[S][H][0-9]{4}\$")
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
            myProductID.error = "Format wrong! eg: SH0001 to SH9999"
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

        val myPattern: Regex = Regex("^[L][O][T][0-9]{3}[A-Z]\$")
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
            myProductLoc.error = "Format wrong! eg. LOC000A - LOC999Z."
        }
        else {
            myProductLoc.error = null
            return true
        }
        return false
    }

    private fun validateProductImg(productImage: ImageView?): Boolean {

        if (productImage?.drawable == null)
        {
            Toast.makeText(this, "No image selected!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text = parent!!.getItemAtPosition(position).toString()
     //   Toast.makeText(parent.context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PHOTO && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                Toast.makeText(this, "No image selected!", Toast.LENGTH_SHORT).show()
                return;
            }

            val productImage = findViewById<ImageView>(R.id.productImage)
            productImage.setImageURI(data.data)

            //val inputStream = contentResolver.openInputStream(data.data!!)
            //val selectedImage = BitmapFactory.decodeStream(inputStream)
            //image!!.setImageBitmap(selectedImage)
            //imageStore(selectedImage)
        }
    }

    private fun AddConstantTextInEditText(edt: EditText, text: String?) {
        edt.setText(text)
        Selection.setSelection(edt.text, edt.text.length)
        edt.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (!s.toString().startsWith(text!!)) {
                    edt.setText(text)
                    Selection.setSelection(edt.text, edt.text.length)
                }
            }
        })
    }
}