package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

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
            startActivity(intent)
        }
    }
}