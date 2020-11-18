package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
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
            val intent = Intent(this, Profile::class.java)
            val myEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
            val myName = findViewById<EditText>(R.id.Username)

            val checkEmail = myEmail.text.toString().isValidEmail()

            // To print error message if false happen for email and pwd
            validateEmail(checkEmail, myEmail)
            val checkName = validateName(myName)

            if(checkEmail && checkName)
            {
                startActivity(intent)
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