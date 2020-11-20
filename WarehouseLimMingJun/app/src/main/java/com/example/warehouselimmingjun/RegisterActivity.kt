package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.warehouselimmingjun.DBHelper.DBHelper
import com.example.warehouselimmingjun.model.Register
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    internal lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        dbHelper = DBHelper(this)

        val login = findViewById<Button>(R.id.login_link)
        login.setOnClickListener(){
            val intent = Intent(this,  Login::class.java)
            startActivity(intent)
        }

        val register = findViewById<Button>(R.id.signUp_button)
        register.setOnClickListener(){

            val intent = Intent(this,  Login::class.java)
            val myEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
            val myPwd = findViewById<EditText>(R.id.editTextTextPwd)
            val myName = findViewById<EditText>(R.id.Username)
            val checkEmail = myEmail.text.toString().isValidEmail()
            val checkPwd = myPwd.text.toString().isValidPasswordFormat()

            // To print error message if false happen for email and pwd
            validateEmail(checkEmail, myEmail)
            validatePwd(checkPwd, myPwd)
            val checkName = validateName(myName)
            val register = Register(
                myEmail.text.toString(),
                myPwd.text.toString(),
                myName.text.toString()
            )
            if(checkEmail && checkPwd && checkName)
            {
                if (dbHelper.checkEmailExist(myEmail.text.toString()))
                {
                    Snackbar.make(it, "Email already Exists, Please enter a new Email", Snackbar.LENGTH_LONG).show()

                }
                else {
                    dbHelper.addregister(register)
                    startActivity(intent)
                }
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

//    private fun String.tt(): Boolean {
//        val passwordREGEX = Pattern.compile("^" +
//                "(?=.*[0-9])" +         //at least 1 digit/
//                "(?=.*[a-z])" +         //at least 1 lower case letter/
//                "(?=.*[A-Z])" +         //at least 1 upper case letter/
//                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character/
//                "(.*\\s.*){1,2}" +      //1 or 2 more white spaces only
//                ".{7,}" +               //at least 7 characters/
//                "$")
//        return passwordREGEX.matcher(this).matches()
//    }

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

    private fun validateName(myName: EditText): Boolean {

        //val myPattern: Regex = Regex("^" + "(.*[A-Za-z])")
        val myPattern: Regex = Regex("^[a-zA-Z]*\$")

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
            //myName.setText(myName.text.toString().toLowerCase().capitalize())
            return true
        }
        return false
    }
}
