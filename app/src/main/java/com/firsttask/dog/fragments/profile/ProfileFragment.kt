package com.firsttask.dog.fragments.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.Application
import com.firsttask.dog.MOBILE_NUMBER
import com.firsttask.dog.R
import com.firsttask.dog.USER_NAME
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentProfileBinding
import com.firsttask.dog.db.entity.Pet
import com.firsttask.dog.fragments.addpet.NewPetFragment
import com.firsttask.dog.fragments.editprofile.EditProfileFragment
import com.firsttask.dog.fragments.profile.adapter.PetAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ProfileViewModelFactory
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentProfileBinding>(
            inflater, R.layout.fragment_profile, container, false
        ).run {
            lifecycleOwner = this@ProfileFragment
            viewModel = this@ProfileFragment.viewModel
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreference: SharedPreferences =
            requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        viewModel.userMobileNumber = sharedPreference.getString(MOBILE_NUMBER, null)
        viewModel.userName.value = sharedPreference.getString(USER_NAME, null)
        (activity as WalkerActivity).hideToolbar()
        onEditProfileClick()
        onAddNewPet()
        viewModel.petItems.observe(viewLifecycleOwner, Observer { createRecyclerView(it) })
    }

    private fun createRecyclerView(arrayList: ArrayList<Pet>) {
        viewModel.getRecyclerViewData()
        val layoutManager: RecyclerView.LayoutManager
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        val adapt = PetAdapter(arrayList)
        recyclerView.adapter = adapt
        recyclerView.layoutManager = layoutManager
    }

    private fun onEditProfileClick() {
        editProfileConstraintLayout.setOnClickListener {
            (activity as WalkerActivity).onScreenStart(EditProfileFragment())
        }
    }

    private fun onAddNewPet(){
        addNewPetButton.setOnClickListener {
            (activity as WalkerActivity).onScreenStart(NewPetFragment())
        }
    }

}
