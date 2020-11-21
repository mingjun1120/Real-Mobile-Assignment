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

class InfoAdapter (
    private val context: Context,
    private val images : List<ItemList>,
    val listener : (ItemList) -> Unit
): RecyclerView.Adapter<InfoAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById<ImageView>(R.id.icon_list)
        val name: TextView = view.findViewById<TextView>(R.id.title_text_view)
        val desc: TextView = view.findViewById<TextView>(R.id.detail_text_view)
        val editBtn: Button = view.findViewById(R.id.buttonEdit)
        val deleteBtn: Button = view.findViewById(R.id.buttonDelete)
        fun bindmodel(item: ItemList) {
            icon.setImageResource(item.icons!!)
            name.text = item.title
            desc.text = item.detail
            editBtn.setOnClickListener {
                listener(item)
            }
            /*deleteBtn.setOnClickListener {
                listener(item)
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_info_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindmodel(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }
}