package com.example.warehouselimmingjun

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmimgjun.adapter.ItemAdapter
import com.example.warehouselimmingjun.model.ItemList

class ItemInfo : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)

        val spinner = findViewById<Spinner>(R.id.categoryspinner)
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.category, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        val backBtn = findViewById<ImageButton>(R.id.backButton)
        backBtn.setOnClickListener {
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val itemList = listOf<ItemList>(
            ItemList(R.drawable.shirt, "ST0001M", "Pink TShirt"),
            ItemList(R.drawable.shirt, "ST0001L", "Pink TShirt")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.imageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemAdapter(this, itemList) {
            //addBtn.setOnClickListener{
            val intent = Intent(this, EditItem::class.java)
            startActivity(intent)
        }
    }

        /*val editBtn = findViewById<ImageButton>(R.id.imageButton3)
        editBtn.setOnClickListener{
            val intent = Intent(this, EditItem::class.java)
            startActivity(intent)
        }*/

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//        val text = parent!!.getItemAtPosition(position).toString()
//        val bb = parent.getItemAtPosition(position).toString()
//        Toast.makeText(parent.context, bb, Toast.LENGTH_SHORT).show()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }
}