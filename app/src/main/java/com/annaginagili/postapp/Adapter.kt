package com.annaginagili.postapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val context: Context, private val itemList: ArrayList<Item>):
    RecyclerView.Adapter<Adapter.ItemHolder>() {
    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val body = itemView.findViewById<TextView>(R.id.body)

        fun setData(item: Item, context: Context) {
            this.title.text = item.title
            this.body.text = item.body

            itemView.setOnClickListener {
                val intent1 = Intent(context, OneByOne::class.java)
                intent1.putExtra("id", item.id)
                context.startActivity(intent1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.post_layout, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item: Item = itemList[position]
        holder.setData(item, context)
    }
}