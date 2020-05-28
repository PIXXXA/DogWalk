package com.firsttask.dog.fragments.announcementlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.R
import com.firsttask.dog.db.entity.model.OrderModel
import kotlinx.android.synthetic.main.fragment_owner_item.view.*

class OwnerAdapter(
    private val recyclerViewItem: List<OrderModel>
) :
    RecyclerView.Adapter<OwnerAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_owner_item, parent, false)
        return OrderViewHolder(v)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val recyclerViewEntity = recyclerViewItem[position]
        recyclerViewEntity.let { holder.binding(it) }
    }

    override fun getItemCount(): Int {
        return recyclerViewItem.size
    }

    class OrderViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        fun binding(ownerModel: OrderModel) {
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

//            itemView.setOnClickListener { _ ->
//                val editPetFragment = EditPetFragment()
//                val bundle = Bundle()
//                bundle.putString(PET_NAME, petModel.name)
//                bundle.putString(PET_DESCRIPTION, petModel.description)
//                bundle.putString(PET_SIZE, petModel.size)
//                bundle.putString(PET_AGE, petModel.age)
//                editPetFragment.arguments = bundle
//                val startActivity = editPetFragment.activity as WalkerActivity
//                startActivity.onScreenStart(editPetFragment)
//            }
        }
    }
}