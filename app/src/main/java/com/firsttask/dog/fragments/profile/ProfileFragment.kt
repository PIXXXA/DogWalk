package com.firsttask.dog.fragments.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.*
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
    private lateinit var sharedPreference: SharedPreferences
    var accountType: Boolean = true

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
        getSharedPreference()
        (activity as WalkerActivity).hideToolbar()
        onEditProfileClick()
        filterAccountType()
    }

    private fun putOwnerSharedPref() {
        viewModel.name.observe(viewLifecycleOwner, Observer {
            val editor = sharedPreference.edit()
            viewModel.id?.let { editor.putLong(OWNER_ID, it) }
            editor.commit()
        })
        if()
        viewModel.getCurrentOwner()
    }

    private fun getSharedPreference(){
        sharedPreference =
            requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        viewModel.userMobileNumber = sharedPreference.getString(MOBILE_NUMBER, null)
        viewModel.userName.value = sharedPreference.getString(USER_NAME, null)
        viewModel.userSurname.value = sharedPreference.getString(USER_SURNAME, null)
        accountType = sharedPreference.getBoolean(ACCOUNT_TYPE, true)
        if(accountType){
            viewModel.updateOwnerTables()
        } else {
            viewModel.updateWalkerTables()
        }
    }

    private fun filterAccountType(){
        if (accountType) {
            profileRecyclerView.visibility = View.VISIBLE
            walkerInfo.visibility = View.GONE
            putOwnerSharedPref()
            onAddNewPet()
            viewModel.petItems.observe(viewLifecycleOwner, Observer { createRecyclerView(it) })
            viewModel.getRecyclerViewData()
        } else {
            viewModel.getCurrentWalker()
            profileRecyclerView.visibility = View.GONE
            addNewPetButton.visibility = View.GONE
            allDogs.setText(R.string.profile_description)
            addNewOrderButton()
        }
    }

    private fun createRecyclerView(arrayList: List<Pet>) {
        Handler().postDelayed({
            val layoutManager: RecyclerView.LayoutManager
            profileRecyclerView.setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            val adapt = PetAdapter(arrayList, activity as WalkerActivity)
            profileRecyclerView.adapter = adapt
            profileRecyclerView.layoutManager = layoutManager
        }, 500)
    }

    private fun onEditProfileClick() {

        editProfileConstraintLayout.setOnClickListener {
            (activity as WalkerActivity).onScreenStart(EditProfileFragment())
        }
    }

    private fun addNewOrderButton() {
        addWalkerNewOrder.setOnClickListener {
            viewModel.updateWalkerTables()
            profileExperience.setText(viewModel.walkerExperience.value)
            profileDescription.setText(viewModel.walkerDescription.value)
            requireActivity().runOnUiThread {
                Toast.makeText(context, R.string.order_walker, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun onAddNewPet() {
        addNewPetButton.setOnClickListener {
            (activity as WalkerActivity).onScreenStart(NewPetFragment())
        }
    }
}
