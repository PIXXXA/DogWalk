package com.firsttask.dog.fragments.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.R
import com.firsttask.dog.db.entity.Pet
import kotlinx.android.synthetic.main.fragment_profile_item.view.*

class PetAdapter(
    private val recyclerViewItem: ArrayList<Pet>
) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_profile_item, parent, false)
        return PetViewHolder(v)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val recyclerViewEntity = recyclerViewItem[position]
        holder.binding(recyclerViewEntity)
    }

    override fun getItemCount(): Int {
        return recyclerViewItem.size
    }

    class PetViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        fun binding(petModel: Pet) {
            itemView.petName.text = petModel.name
        }
    }
}