package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmingjun.DBHelper.DBHelper_item
import com.example.warehouselimmingjun.adapter.InfoAdapter
import com.example.warehouselimmingjun.model.Item

class ItemInfo : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    internal lateinit var dbHelper: DBHelper_item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)
        var productList = listOf<Item>()
        var shoeList = listOf<Item>()
        var shirtList = listOf<Item>()
        dbHelper = DBHelper_item(this)

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener {
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.imageRecyclerView)

        val spinner = findViewById<Spinner>(R.id.categoryspinner)

        val adapter = ArrayAdapter.createFromResource(
            this, R.array.category, R.layout.custom_spinner
        )
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent!!.getItemAtPosition(position).toString()
                if (selectedItem == "Shirt") {
                    shirtList = dbHelper.retrieveShirtItem()
                    recyclerView.layoutManager = LinearLayoutManager(parent.context)
                    recyclerView.adapter = InfoAdapter(parent.context, shirtList) {
                        //addBtn.setOnClickListener{
                        val intent = Intent(parent.context, EditItem::class.java)
                        startActivity(intent)
                    }
                }
                else if(selectedItem == "Shoe")
                {
                    shoeList = dbHelper.retrieveShoesItem()
                    recyclerView.layoutManager = LinearLayoutManager(parent.context)
                    recyclerView.adapter = InfoAdapter(parent.context, shoeList) {
                        //addBtn.setOnClickListener{
                        val intent = Intent(parent.context, EditItem::class.java)
                        startActivity(intent)
                    }
                }
                else {
                    productList = dbHelper.retrieveAllItem()
                    recyclerView.layoutManager = LinearLayoutManager(parent.context)
                    recyclerView.adapter = InfoAdapter(parent.context, productList) {
                        //addBtn.setOnClickListener{
                        val intent = Intent(parent.context, EditItem::class.java)
                        startActivity(intent)
                    }
                }
            }

        }

    }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }
}