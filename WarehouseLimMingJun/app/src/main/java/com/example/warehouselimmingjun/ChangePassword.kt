package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val saveBtn = findViewById<Button>(R.id.btn_to_save_new_pwd)
        saveBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            val myPwd = findViewById<EditText>(R.id.newPassword)
            val myConfirmPwd = findViewById<EditText>(R.id.confirm_new_pwd)

            val checkPwd = myPwd.text.toString().isValidPasswordFormat()
            val checkConfirmPwd = myConfirmPwd.text.toString() == myPwd.text.toString()

            // To print error message if false happen for email and pwd
            validatePwd(checkPwd, myPwd)
            validateConfirmPwd(checkConfirmPwd, myConfirmPwd)

            if(checkPwd && checkConfirmPwd)
            {
                startActivity(intent)
            }
        }
    }

    private fun validateConfirmPwd(checkConfirmPwd: Boolean, myConfirmPwd: EditText) {
        if (myConfirmPwd.text.toString().isEmpty())
        {
            myConfirmPwd.error = "Field can't be empty"
        }
        else if (!checkConfirmPwd)
        {
            myConfirmPwd.error = "Confirm password and new password must similar"
        }
        else
        {
            myConfirmPwd.error = null
        }
    }

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