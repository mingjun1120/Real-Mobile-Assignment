package com.example.warehouselimmingjun.adapter

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmingjun.DBHelper.DBHelper_item
import com.example.warehouselimmingjun.EditItem
import com.example.warehouselimmingjun.HomeScreen
import com.example.warehouselimmingjun.ItemInfo
import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.model.Item

class InfoAdapter(private val context: Context, private val images: List<Item>,val sessionId:String?, val sessionId1:String?, val listener: (Item) -> Unit):
    RecyclerView.Adapter<InfoAdapter.ItemViewHolder>()
    {
        internal lateinit var dbHelper: DBHelper_item

        inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val icon: ImageView = view.findViewById<ImageView>(R.id.icon_list)
            val name: TextView = view.findViewById<TextView>(R.id.title_text_view)
            val desc: TextView = view.findViewById<TextView>(R.id.detail_text_view)
            val editBtn: Button = view.findViewById(R.id.buttonEdit)
            val deleteBtn: Button = view.findViewById(R.id.buttonDelete)

            fun bindmodel(item: Item)
            {
                dbHelper = DBHelper_item(context)
                val options: BitmapFactory.Options? = BitmapFactory.Options()
                val bitmap = BitmapFactory.decodeByteArray(item.image, 0, item.image!!.size, options)
                icon.setImageBitmap(bitmap)
                name.text = item.id
                desc.text = item.name
                val sessionId = sessionId
                val sessionId1 = sessionId1

                deleteBtn.setOnClickListener {
                    val builder = AlertDialog.Builder(context)
                    //set title for alert dialog
                    builder.setTitle("Delete Item Confirmation")
                    //set message for alert dialog
                    builder.setMessage("Confirm Delete Item?")
                    //builder.setIcon(android.R.drawable.ic_dialog_alert)

                    //performing positive action

                        builder.setPositiveButton("Confirm",
                            DialogInterface.OnClickListener { dialog, id ->
                                if(dbHelper.deleteProduct(item.id.toString())) {
                                    Toast.makeText(
                                    context,
                                    "Item deleted successful!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                                val intent = Intent(context, ItemInfo::class.java)
                                intent.putExtra("emailAddress", sessionId)
                                intent.putExtra("name", sessionId1)
                                context.startActivity(intent)
                            }
                    })
                    //performing negative action
                    builder.setNegativeButton("Cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            Toast.makeText(context, "Cancellation successful!", Toast.LENGTH_SHORT)
                                .show()
                        })

                    //Create the AlertDialog
                    val alertDialog: AlertDialog = builder.create()
                    //set other dialog properties
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_info_list, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bindmodel(images[position])

            holder.editBtn.setOnClickListener {

                //Get position on selected item
                val model = images[position]

                val id = model.id
                val location = model.location
                val name = model.name
                val qty = model.quantity
                val price = model.price
                val category = model.category
                val size = model.size
                val myImage = model.image

                //Create intent with kotlin
                val intent = Intent(context, EditItem::class.java)
                // Now put all these items with put extra intent
                intent.putExtra("ProductID", id)
                intent.putExtra("ProductLoc", location)
                intent.putExtra("ProductName", name)
                intent.putExtra("ProductQty", qty)
                intent.putExtra("ProductPrice", price)
                intent.putExtra("ProductCategory", category)
                intent.putExtra("ProductSize", size)
                intent.putExtra("ProductImg", myImage)
                //Start another activity
                context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return images.size
        }
    }