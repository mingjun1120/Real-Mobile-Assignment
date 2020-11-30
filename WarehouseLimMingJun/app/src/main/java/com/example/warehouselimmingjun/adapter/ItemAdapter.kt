package com.example.warehouselimmimgjun.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmingjun.EditItem
import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.model.Item
import com.example.warehouselimmingjun.stockInForm


class ItemAdapter(
    private val context: Context,
    private val images: List<Item>,
    val sessionId: String?,
    val sessionId1: String?,
    val category: String?,
    val listener: (Item) -> Unit
): RecyclerView.Adapter<ItemAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById<ImageView>(R.id.icon_list)
        val name: TextView = view.findViewById<TextView>(R.id.title_text_view)
        val desc: TextView = view.findViewById<TextView>(R.id.detail_text_view)
        val addBtn: Button = view.findViewById(R.id.button)

        fun bindmodel(item: Item) {

           val options: BitmapFactory.Options? = BitmapFactory.Options()
           val bitmap = BitmapFactory.decodeByteArray(item.image, 0, item.image!!.size, options)

            icon.setImageBitmap(bitmap)
            name.text = item.id
            desc.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(context).inflate(R.layout.product_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindmodel(images[position])

        holder.addBtn.setOnClickListener {

            //Get Shirt or Shoes
            val shirtOrShoes = category

            //Get user id and email
            val sessionId = sessionId
            val sessionId1 = sessionId1

            //Get position on selected item
            val model = images[position]

            val category = model.category
            val name = model.name
            val size = model.size
            val qty = model.quantity

            //Create intent with kotlin
            val intent = Intent(context, stockInForm::class.java)

            // Now put all these items with put extra intent
            intent.putExtra("ProductCategory", category)
            intent.putExtra("ProductName", name)
            intent.putExtra("ProductSize", size)
            intent.putExtra("ProductQty", qty)
            intent.putExtra("emailAddress", sessionId)
            intent.putExtra("name", sessionId1)

            if (shirtOrShoes.equals("shirt"))
            {
                intent.putExtra("Shirt", shirtOrShoes)
            }
            else
            {
                intent.putExtra("Shoes", shirtOrShoes)
            }

            //Start another activity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}