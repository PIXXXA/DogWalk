package com.firsttask.dog.fragments.announcementlist.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.*
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.db.entity.Walker
import com.firsttask.dog.fragments.orderdetails.OrderDetailsFragment
import kotlinx.android.synthetic.main.fragment_walker_item.view.*

class WalkerAdapter(
    private val recyclerViewItem: List<Walker>,
    private val activity: WalkerActivity?
) :
    RecyclerView.Adapter<WalkerAdapter.WalkerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalkerViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_walker_item, parent, false)
        return WalkerViewHolder(v)
    }

    override fun onBindViewHolder(holder: WalkerViewHolder, position: Int) {
        val recyclerViewEntity = recyclerViewItem[position]
        recyclerViewEntity.let { holder.binding(it, activity) }
    }

    override fun getItemCount(): Int {
        return recyclerViewItem.size
    }

    class WalkerViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        fun binding(walkerModel: Walker, activity: WalkerActivity?) {
            itemView.walkerName.text = walkerModel.name.plus(" ").plus(walkerModel.surname)
            itemView.walkerExperience.text =
                itemView.context.getString(
                    R.string.all_walker_experience,
                    walkerModel.experience
                )
            itemView.walkerDescription.text =
                itemView.context.getString(
                    R.string.all_walker_description,
                    walkerModel.description
                )

            itemView.setOnClickListener { _ ->
                val orderDetailsFragment = OrderDetailsFragment()
                val bundle = Bundle()
                bundle.putString(WALKER_NAME, walkerModel.name)
                bundle.putString(WALKER_SURNAME, walkerModel.surname)
                bundle.putString(WALKER_MOBILE_NUMBER, walkerModel.mobileNumber)
                bundle.putString(WALKER_EXPERIENCE, walkerModel.experience)
                bundle.putString(WALKER_DESCRIPTION, walkerModel.description)
                orderDetailsFragment.arguments = bundle
                activity?.onScreenStart(orderDetailsFragment)
            }
        }
    }
}