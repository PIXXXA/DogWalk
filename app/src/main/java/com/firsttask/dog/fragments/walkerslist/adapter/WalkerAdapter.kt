package com.firsttask.dog.fragments.walkerslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.R
import com.firsttask.dog.db.entity.User
import kotlinx.android.synthetic.main.fragment_walker_item.view.*

class WalkerAdapter(
    private val recyclerViewItem: ArrayList<User>
) :
    RecyclerView.Adapter<WalkerAdapter.MyViewHolder>() {

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
            itemView.walkerName.text = user.name.plus(" ").plus(user.surname)
            itemView.walkerExperience.text = itemView.context.getString(
                R.string.all_walker_experience,
                user.experience.toString()
            )
            itemView.walkerDescription.text = user.description
        }
    }
}