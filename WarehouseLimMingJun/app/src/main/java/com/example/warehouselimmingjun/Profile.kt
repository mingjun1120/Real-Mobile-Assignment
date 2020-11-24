package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import com.example.warehouselimmingjun.DBHelper.DBHelper

class Profile : AppCompatActivity() {

    internal lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        dbHelper = DBHelper(this)

        val textView5 = findViewById<TextView>(R.id.textView5)
        val textView6 = findViewById<TextView>(R.id.textView6)
        val hello_username = findViewById<TextView>(R.id.hello_Username)
      //  val profileEmail = dbHelper.getEmail()
       // val profileName = dbHelper.getName(profileEmail)
      //  val string = "Hello,$profileName"
        val intent = intent
        val sessionId = getIntent().getStringExtra("emailAddress")
        val sessionId1 = getIntent().getStringExtra("name")
        textView5.text = sessionId
        hello_username.text = sessionId1
        textView6.text = sessionId1
        //textView5.text = profileEmail
      //  hello_username.text = string
        //textView6.text = profileName

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
        val chgPwdBtn = findViewById<Button>(R.id.btn_to_chg_pwd)
        chgPwdBtn.setOnClickListener{
            val intent = Intent(this, ChangePassword::class.java)
            startActivity(intent)
        }
        val editProfileBtn = findViewById<Button>(R.id.btn_to_editProfile)
        editProfileBtn.setOnClickListener{
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }
    }
}