package com.example.warehouselimmingjun.adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.model.Item
import com.example.warehouselimmingjun.model.ItemList
import com.example.warehouselimmingjun.stockOutForm

class ItemOutAdapter (
    private val context: Context,
    private val images : List<Item>,
    val listener : (Item) -> Unit
): RecyclerView.Adapter<ItemOutAdapter.ItemOutViewHolder>() {
    inner class ItemOutViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById<ImageView>(R.id.icon_list)
        val name: TextView = view.findViewById<TextView>(R.id.title_text_view)
        val desc: TextView = view.findViewById<TextView>(R.id.detail_text_view)
        val outBtn: Button = view.findViewById(R.id.button)

        fun bindmodel(item: Item) {

            val options: BitmapFactory.Options? = BitmapFactory.Options()
            val bitmap = BitmapFactory.decodeByteArray(item.image, 0, item.image!!.size, options)

            icon.setImageBitmap(bitmap)
            name.text = item.id
            desc.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemOutAdapter.ItemOutViewHolder {
        return ItemOutViewHolder(
            LayoutInflater.from(context).inflate(R.layout.product_list_out, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemOutViewHolder, position: Int) {
        holder.bindmodel(images[position])

        holder.outBtn.setOnClickListener {

            //Get position on selected item
            val model = images[position]

            val category = model.category
            val name = model.name
            val size = model.size
            val qty = model.quantity

            //Create intent with kotlin
            val intent = Intent(context, stockOutForm::class.java)

            // Now put all these items with put extra intent
            intent.putExtra("ProductCategory", category)
            intent.putExtra("ProductName", name)
            intent.putExtra("ProductSize", size)
            intent.putExtra("ProductQty", qty)

            //Start another activity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}