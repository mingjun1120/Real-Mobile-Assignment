package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val login = findViewById<Button>(R.id.login_button)
        login.setOnClickListener(){
            val intent = Intent(this,  Login::class.java)
            startActivity(intent)
        }

        val register = findViewById<Button>(R.id.login_link)
        register.setOnClickListener(){
            val intent = Intent(this,  Login::class.java)
            val myEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
            val myPwd = findViewById<EditText>(R.id.editTextTextPwd)
            val checkEmail = myEmail.text.toString().isValidEmail()
            val checkPwd = myPwd.text.toString().isValidPasswordFormat()

            // To print error message if false happen for email and pwd
            validateEmail(checkEmail, myEmail)
            validatePwd(checkPwd, myPwd)

            if(checkEmail && checkPwd)
            {
                startActivity(intent)
            }
        }
    }

    private fun String.isValidEmail() = !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isValidPasswordFormat(): Boolean {
        val passwordREGEX = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit/
                "(?=.*[a-z])" +         //at least 1 lower case letter/
                "(?=.*[A-Z])" +         //at least 1 upper case letter/
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character/
                "(?=\\S+$)" +           //no white spaces
                ".{7,}" +               //at least 7 characters/
                "$")
        return passwordREGEX.matcher(this).matches()
    }

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

    private fun validatePwd(checkPwd: Boolean, myPwd: EditText) {
        if (myPwd.text.toString().isEmpty())
        {
            myPwd.error = "Field can't be empty"
        }
        else if (!checkPwd)
        {
            myPwd.error = "Password too weak!"
        }
        else
        {
            myPwd.error = null
        }
    }
}