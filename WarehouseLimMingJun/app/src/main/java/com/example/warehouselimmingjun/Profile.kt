package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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