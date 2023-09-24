package com.example.steelindia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


    class ItemAdapter( var list: ArrayList<Item> , val itemClickListener: ItemClickListener) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name: TextView = itemView.findViewById(R.id.tvName)
            val price: TextView = itemView.findViewById(R.id.tvPrice)
            val quantity: TextView = itemView.findViewById(R.id.tvQuantity)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val currentItemData = list[position]

            // Assuming that each ArrayList<String> has at least three elements.
            holder.name.text = currentItemData.itemName
            holder.price.text = currentItemData.itemPrice.toString()
            holder.quantity.text = currentItemData.itemQuantity.toString()
            holder.itemView.setOnClickListener{itemClickListener.onItemClick(currentItemData.itemId)}
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }
interface ItemClickListener{
    fun onItemClick(itemId: Long)
}
