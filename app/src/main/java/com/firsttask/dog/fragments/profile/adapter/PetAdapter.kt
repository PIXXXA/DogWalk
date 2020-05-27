package com.firsttask.dog.fragments.profile.adapter

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.PET_AGE
import com.firsttask.dog.PET_DESCRIPTION
import com.firsttask.dog.PET_NAME
import com.firsttask.dog.PET_SIZE
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.db.entity.Pet
import com.firsttask.dog.fragments.editpet.EditPetFragment
import kotlinx.android.synthetic.main.fragment_profile_item.view.*

class PetAdapter(
    private val recyclerViewItem: List<Pet>
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

            itemView.setOnClickListener { _ ->
                val editPetFragment = EditPetFragment()
                val bundle = Bundle()
                bundle.putString(PET_NAME, petModel.name)
                bundle.putString(PET_DESCRIPTION, petModel.description)
                bundle.putString(PET_SIZE, petModel.size)
                bundle.putString(PET_AGE, petModel.age)
                editPetFragment.arguments = bundle
                val startActivity = editPetFragment.activity as WalkerActivity
                startActivity.onScreenStart(editPetFragment)
            }
        }
    }
}