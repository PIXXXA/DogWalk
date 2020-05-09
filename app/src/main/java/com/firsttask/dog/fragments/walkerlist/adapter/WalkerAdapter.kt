package com.firsttask.dog.fragments.walkerlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.R
import com.firsttask.dog.db.entity.User
import kotlinx.android.synthetic.main.fragment_walker_item.view.*

class WalkerAdapter(private val recyclerViewItem: ArrayList<WalkerEntity>) : RecyclerView.Adapter<WalkerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_walker_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recyclerViewEntity = recyclerViewItem[position]
        holder.binding(recyclerViewEntity)
    }

    override fun getItemCount(): Int {
        return recyclerViewItem.size
    }

    class MyViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        fun binding(user: User) {
            itemView.walkerDescription.text = user.
            itemView.coordinateTextView.text =
            itemView.bodyKeyTextView.text =
        }
    }
}