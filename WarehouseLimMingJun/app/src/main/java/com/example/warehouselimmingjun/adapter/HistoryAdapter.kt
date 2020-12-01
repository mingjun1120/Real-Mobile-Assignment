package com.example.warehouselimmingjun.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.history_detail
import com.example.warehouselimmingjun.model.HistoryList

class HistoryAdapter (private val context: Context, private val images: List<HistoryList>, val listener: (HistoryList) -> Unit): RecyclerView.Adapter<HistoryAdapter.ItemViewHolder>()
{
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById<ImageView>(R.id.icon_list)
        val name: TextView = view.findViewById<TextView>(R.id.title_text_view)
        val desc: TextView = view.findViewById<TextView>(R.id.detail_text_view)
        val stockIn: TextView = view.findViewById<TextView>(R.id.stock_in)
        val stockInQty: TextView = view.findViewById<TextView>(R.id.stock_in_text_view)
        val stockOut: TextView = view.findViewById<TextView>(R.id.stock_out)
        val stockOutQty: TextView = view.findViewById<TextView>(R.id.stock_out_text_view)
        val date: TextView = view.findViewById<TextView>(R.id.date_time_text_view)
        val viewBtn: Button = view.findViewById(R.id.buttonView)

        fun bindmodel(item: HistoryList)
        {
            icon.setImageResource(item.icons!!)
            name.text = item.title
            desc.text = item.detail
            stockIn.text = item.stockIn
            stockInQty.text = item.stockInQty
            stockOut.text = item.stockOut
            stockOutQty.text = item.stockOutQty
            date.text = item.dateTime
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(context).inflate(R.layout.transaction_history_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindmodel(images[position])

        holder.viewBtn.setOnClickListener {

            //Get user id and email
            //val sessionId = sessionId
            //val sessionId1 = sessionId1

            //Get position on selected item
            val model = images[position]

//            val id = model.id
//            val location = model.location
//            val name = model.name
//            val qty = model.quantity
//            val price = model.price
//            val category = model.category
//            val size = model.size
//            val myImage = model.image

            //Create intent with kotlin
            val intent = Intent(context, history_detail::class.java)
            // Now put all these items with put extra intent
//            intent.putExtra("ProductID", id)
//            intent.putExtra("ProductLoc", location)
//            intent.putExtra("ProductName", name)
//            intent.putExtra("ProductQty", qty)
//            intent.putExtra("ProductPrice", price)
//            intent.putExtra("ProductCategory", category)
//            intent.putExtra("ProductSize", size)
//            intent.putExtra("ProductImg", myImage)

            //intent.putExtra("emailAddress", sessionId)
            //intent.putExtra("name", sessionId1)

            //Start another activity
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}