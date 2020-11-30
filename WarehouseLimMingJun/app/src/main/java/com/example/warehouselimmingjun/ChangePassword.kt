package com.example.warehouselimmingjun

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.warehouselimmingjun.DBHelper.DBHelper
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class ChangePassword : AppCompatActivity() {

    internal lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        dbHelper = DBHelper(this)

        val sessionId = intent.getStringExtra("emailAddress")
        val sessionId1 = intent.getStringExtra("name")

        val hello_username = findViewById<TextView>(R.id.hello_Username)
        hello_username.text = "Hello, $sessionId1"

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            intent.putExtra("emailAddress", sessionId)
            intent.putExtra("name", sessionId1)
            startActivity(intent)
        }

        val saveBtn = findViewById<Button>(R.id.btn_to_save_new_pwd)
        saveBtn.setOnClickListener{
            //val intent = Intent(this, MainActivity::class.java)
            val myPwd = findViewById<EditText>(R.id.newPassword)
            val myConfirmPwd = findViewById<EditText>(R.id.confirm_new_pwd)

            val checkPwd = myPwd.text.toString().isValidPasswordFormat()
            val checkConfirmPwd = myConfirmPwd.text.toString() == myPwd.text.toString()

            var i = 1
            // To print error message if false happen for email and pwd
            if (dbHelper.checkPassExist(myPwd.text.toString()))
            {
                i=0
                val builder = AlertDialog.Builder(this)
                Snackbar.make(it, "New Password Cannot Same With Old Password", Snackbar.LENGTH_LONG).show()

                //set title for alert dialog
                builder.setTitle("New Password Cannot Same With Old Password")
                //set message for alert dialog
                builder.setMessage("Please Enter Again!")
                //builder.setIcon(android.R.drawable.ic_dialog_alert)

                //performing positive action
                builder.setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                        val intent = Intent(this, ChangePassword::class.java)
                        intent.putExtra("emailAddress", sessionId)
                        intent.putExtra("name", sessionId1)
                        startActivity(intent)
                    })

                //performing negative action
                builder.setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        val intent = Intent(this, ChangePassword::class.java)
                        intent.putExtra("emailAddress", sessionId)
                        intent.putExtra("name", sessionId1)
                        startActivity(intent)
                    })

                //Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                //set other dialog properties
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
            validatePwd(checkPwd, myPwd)
            validateConfirmPwd(checkConfirmPwd, myConfirmPwd)

            if(i == 1){
            if(checkPwd && checkConfirmPwd)
            {
                val builder = AlertDialog.Builder(this)

                //set title for alert dialog
                builder.setTitle("Change Password Confirmation")
                //set message for alert dialog
                builder.setMessage("Confirm Change Password?")
                //builder.setIcon(android.R.drawable.ic_dialog_alert)

                //performing positive action
               if( dbHelper.updatePass(myConfirmPwd.text.toString(),sessionId.toString())) {
                   builder.setPositiveButton("Confirm",
                       DialogInterface.OnClickListener { dialog, id ->
                           Toast.makeText(
                               this,
                               "Password changed successfully!",
                               Toast.LENGTH_SHORT
                           ).show()
                           val intent = Intent(this, MainActivity::class.java)
                           startActivity(intent)
                       })
               }

                //performing negative action
                builder.setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(this, "Cancelled change password!", Toast.LENGTH_SHORT).show()
                    })

                //Create the AlertDialog
                val alertDialog: AlertDialog = builder.create()
                //set other dialog properties
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
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