package com.example.warehouselimmimgjun.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouselimmingjun.R
import com.example.warehouselimmingjun.model.Product

/**
 * Adapter for the [RecyclerView] in [Stock In list]. Displays [Product] data object.
 */
class ItemAdapter(
    private val context: Context,
    private val dataset: List<Product>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    class ItemViewHolder(itemView: View, var mListener:OnItemClickListener): RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val textView: TextView = itemView.findViewById(R.id.item_title)
        val imageView: ImageView = itemView.findViewById(R.id.item_image)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if(mListener != null){
                mListener.setOnClickListener((adapterPosition))
            }
        }
    }

    private lateinit var mListener: OnItemClickListener
    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list, parent, false)

        return ItemViewHolder(adapterLayout, mListener)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)


    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size

    interface OnItemClickListener{
        fun setOnClickListener(pos:Int)
    }

    fun setOnItemClickListener(mlistener:OnItemClickListener){
        this.mListener = mListener
    }
}