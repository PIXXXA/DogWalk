package com.firsttask.dog.fragments.announcementlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.R
import com.firsttask.dog.db.entity.Walker
import kotlinx.android.synthetic.main.fragment_walker_item.view.*

class WalkerAdapter(
    private val recyclerViewItem: List<Walker>?
) :
    RecyclerView.Adapter<WalkerAdapter.WalkerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalkerViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_walker_item, parent, false)
        return WalkerViewHolder(v)
    }

    override fun onBindViewHolder(holder: WalkerViewHolder, position: Int) {
        val recyclerViewEntity = recyclerViewItem?.get(position)
        recyclerViewEntity?.let { holder.binding(it) }
    }

    override fun getItemCount(): Int {
        return recyclerViewItem?.size!!
    }

    class WalkerViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        fun binding(walkerModel: Walker) {
            itemView.walkerName.text = walkerModel.name.plus(" ").plus(walkerModel.surname)
            itemView.walkerExperience.text =
                itemView.context.getString(
                    R.string.all_walker_experience,
                    walkerModel.experience
                )
            itemView.walkerDescription.text = walkerModel.description
        }
    }
}