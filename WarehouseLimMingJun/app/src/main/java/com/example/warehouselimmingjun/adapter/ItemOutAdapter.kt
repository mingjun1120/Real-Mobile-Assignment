package com.example.warehouselimmingjun.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.model.ItemList

class ItemOutAdapter (
    private val context: Context,
    private val images : List<ItemList>,
    val listener : (ItemList) -> Unit
): RecyclerView.Adapter<ItemOutAdapter.ItemOutViewHolder>() {
    inner class ItemOutViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById<ImageView>(R.id.icon_list)
        val name: TextView = view.findViewById<TextView>(R.id.title_text_view)
        val desc: TextView = view.findViewById<TextView>(R.id.detail_text_view)
        val outBtn: Button = view.findViewById(R.id.button)
        fun bindmodel(item: ItemList) {
            icon.setImageResource(item.icons!!)
            name.text = item.title
            desc.text = item.detail
            outBtn.setOnClickListener {
                listener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemOutAdapter.ItemOutViewHolder {
        return ItemOutViewHolder(
            LayoutInflater.from(context).inflate(R.layout.product_list_out, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemOutViewHolder, position: Int) {
        holder.bindmodel(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }
}