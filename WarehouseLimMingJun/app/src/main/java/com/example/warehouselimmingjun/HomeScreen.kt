package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu

class HomeScreen : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val rightIcon = findViewById<ImageView>(R.id.right_icon_menu)

        rightIcon.setOnClickListener {
            val popupMenu = PopupMenu(this@HomeScreen, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId)
                {
                    R.id.one -> {
                        //val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://resocoder.com"))
                        //startActivity(intent)
                        val intent = Intent(this, Profile::class.java)
                        val sessionId = getIntent().getStringExtra("emailAddress")
                        val sessionId1 = getIntent().getStringExtra("name")
                        intent.putExtra("emailAddress", sessionId);
                        intent.putExtra("name", sessionId1);
                        startActivity(intent)
                        //Toast.makeText(this@HomeScreen, "Profile", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.two -> {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        //Toast.makeText(this@HomeScreen, "Log Out!", Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> false
                }
            }

            popupMenu.inflate(R.menu.popup_menu)

            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.e("Main", "Error showing menu icons.", e)
            } finally {
                popupMenu.show()
            }
        }

        val stockInBtn = findViewById<ImageButton>(R.id.buttonStockIn)
        stockInBtn.setOnClickListener{
            val intent = Intent(this, StockIn::class.java)
            startActivity(intent)
        }

        val stockOutBtn = findViewById<ImageButton>(R.id.buttonStockOut)
        stockOutBtn.setOnClickListener{
            val intent = Intent(this, StockOut::class.java)
            startActivity(intent)
        }

        val addNewItemBtn = findViewById<ImageButton>(R.id.buttonAddNewItem)
        addNewItemBtn.setOnClickListener{
            val intent = Intent(this, AddNewItem::class.java)
            startActivity(intent)
        }

        val itemInfoBtn = findViewById<ImageButton>(R.id.buttonItemInfo)
        itemInfoBtn.setOnClickListener{
            val intent = Intent(this, ItemInfo::class.java)
            startActivity(intent)
        }

    }
}