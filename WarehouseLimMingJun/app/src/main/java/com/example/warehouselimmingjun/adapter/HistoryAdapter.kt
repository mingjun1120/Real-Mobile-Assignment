package com.example.warehouselimmingjun.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmingjun.DBHelper.DBHelper_item
import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.history_detail
import com.example.warehouselimmingjun.model.History
import com.example.warehouselimmingjun.model.HistoryList

class HistoryAdapter(
    private val context: Context,
    private val images: List<History>,
    val sessionId: String?,
    val sessionId1: String?,
    val listener: (History) -> Unit
): RecyclerView.Adapter<HistoryAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById<ImageView>(R.id.icon_list)
        val name: TextView = view.findViewById<TextView>(R.id.title_text_view)
        val desc: TextView = view.findViewById<TextView>(R.id.detail_text_view)
        val stockInQty: TextView = view.findViewById<TextView>(R.id.stock_in_text_view)
        val stockOutQty: TextView = view.findViewById<TextView>(R.id.stock_out_text_view)
        val date: TextView = view.findViewById<TextView>(R.id.date_time_text_view)

        val viewBtn: Button = view.findViewById(R.id.buttonView)

        fun bindmodel(history: History) {
            val options: BitmapFactory.Options? = BitmapFactory.Options()
            val bitmap =  BitmapFactory.decodeByteArray(history.Image, 0, history.Image!!.size, options)
            icon.setImageBitmap(bitmap)
            name.text = history.ProductId
            desc.text = history.ProductName
            stockInQty.text = history.StockIn
            stockOutQty.text = history.StockOut
            date.text = history.HistoryDate

            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.transaction_history_list, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bindmodel(images[position])

            holder.viewBtn.setOnClickListener {

                //Get user id and email
                val sessionId = sessionId
                val sessionId1 = sessionId1

                //Get position on selected item
                val model = images[position]

                val id = model.ProductId
                val name = model.ProductName
                val stockIn = model.StockIn
                val stockOut = model.StockOut
                val image = model.Image
                val date = model.HistoryDate
                val username = model.UserName


                //Create intent with kotlin
                val intent = Intent(context, history_detail::class.java)
                // Now put all these items with put extra intent
              intent.putExtra("ProductID", id)
              intent.putExtra("ProductName", name)
              intent.putExtra("ProductStockInQty", stockIn)
              intent.putExtra("ProductStockOutQty", stockOut)
              intent.putExtra("ProductImage", image)
              intent.putExtra("HistoryDate", date)
              intent.putExtra("UserName", username)
              intent.putExtra("emailAddress", sessionId)
              intent.putExtra("name", sessionId1)

                //Start another activity
                context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return images.size
        }
    }
