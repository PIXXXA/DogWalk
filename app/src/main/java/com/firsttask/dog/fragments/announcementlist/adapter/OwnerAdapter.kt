package com.firsttask.dog.fragments.announcementlist.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.*
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.db.entity.model.OrderModel
import com.firsttask.dog.fragments.orderdetails.OrderDetailsFragment
import kotlinx.android.synthetic.main.fragment_owner_item.view.*

class OwnerAdapter(
    private val recyclerViewItem: List<OrderModel>,
    private val activity: WalkerActivity?
) :
    RecyclerView.Adapter<OwnerAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_owner_item, parent, false)
        return OrderViewHolder(v)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val recyclerViewEntity = recyclerViewItem[position]
        recyclerViewEntity.let { holder.binding(it, activity) }
    }

    override fun getItemCount(): Int {
        return recyclerViewItem.size
    }

    class OrderViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        fun binding(ownerModel: OrderModel, activity: WalkerActivity?) {
            itemView.ownerName.text = ownerModel.ownerName.plus(" ").plus(ownerModel.ownerSurname)
            itemView.ownerDate.text = ownerModel.orderDate
            itemView.ownerPetName.text =
                itemView.context.getString(
                    R.string.order_pet_name,
                    ownerModel.petName
                )
            itemView.ownerPetAge.text =
                itemView.context.getString(
                    R.string.order_pet_age,
                    ownerModel.petAge
                )
            itemView.ownerPetSize.text =
                itemView.context.getString(
                    R.string.order_pet_size,
                    ownerModel.petSize
                )

            itemView.setOnClickListener { _ ->
                val orderDetailsFragment = OrderDetailsFragment()
                val bundle = Bundle()
                bundle.putString(OWNER_DATE, ownerModel.orderDate)
                bundle.putString(OWNER_PET_NAME, ownerModel.petName)
                bundle.putString(OWNER_PET_AGE, ownerModel.petAge)
                bundle.putString(OWNER_PET_DESCRIPTION, ownerModel.petDescription)
                bundle.putString(OWNER_PET_SIZE, ownerModel.petSize)
                bundle.putString(OWNER_NAME, ownerModel.ownerName)
                bundle.putString(OWNER_SURNAME, ownerModel.ownerSurname)
                bundle.putString(OWNER_MOBILE_NUMBER, ownerModel.ownerMobileNumber)
                orderDetailsFragment.arguments = bundle
                activity?.onScreenStart(orderDetailsFragment)
            }
        }
    }
}