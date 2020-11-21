package com.example.warehouselimmingjun

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val updateBtn = findViewById<Button>(R.id.btn_to_upd_profileEdit)
        updateBtn.setOnClickListener{
            //val intent = Intent(this, Profile::class.java)
            val myEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
            val myName = findViewById<EditText>(R.id.Username)

            val checkEmail = myEmail.text.toString().isValidEmail()

            // To print error message if false happen for email and pwd
            validateEmail(checkEmail, myEmail)
            val checkName = validateName(myName)

            if(checkEmail && checkName)
            {
                val builder = AlertDialog.Builder(this)
                //set title for alert dialog
                builder.setTitle("Edit Profile Confirmation")
                //set message for alert dialog
                builder.setMessage("Confirm Edit Profile?")
                //builder.setIcon(android.R.drawable.ic_dialog_alert)

                //performing positive action
                builder.setPositiveButton("Confirm",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this,"Profile updated successfully!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Profile::class.java)
                        startActivity(intent)
                    })

                //performing negative action
                builder.setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this, "Cancelled Edit Profile!", Toast.LENGTH_SHORT).show()
                    });

                //Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                //set other dialog properties
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
    }

    private fun String.isValidEmail() = !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun validateEmail(checkEmail: Boolean, myEmail: EditText) {

        if (myEmail.text.toString().isEmpty())
        {
            myEmail.error = "Field can't be empty"
        }
        else if (!checkEmail)
        {
            myEmail.error = "Invalid input for Email!"
        }
        else
        {
            myEmail.error = null
        }
    }

    private fun validateName(myName: EditText): Boolean {

        val myPattern: Regex = Regex("^[a-zA-Z]*\$")
//        val myPattern: Regex = Regex("^" + "[a-zA-Z]")

        if (myName.text.toString().isEmpty()) {
            myName.error = "Field can't be empty!"
        }
        else if (myName.text.toString().contains(" ")) {
            myName.error = "No whitespace!"
        }
        else if (!(myPattern.matches(myName.text.toString()))) {
            myName.error = "Only English alphabet characters!"
        }
        else if (myName.text.toString().length !in 3..10) {
            myName.error = "At least 3 to 10 characters long!"
        }

        else {
            myName.error = null
            return true
        }
        return false
    }
}