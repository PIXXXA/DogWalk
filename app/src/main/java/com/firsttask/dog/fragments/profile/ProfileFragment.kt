package com.firsttask.dog.fragments.profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import com.firsttask.dog.activity.LoginActivity
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentProfileBinding
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
    lateinit var adapt: PetAdapter

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
        filterAccountType()
        onEditProfileClick()
        onExitAccountButtonClick()
        createRecyclerView()
    }

    private fun putOwnerSharedPref() {
        viewModel.getCurrentOwner().observe(viewLifecycleOwner, Observer {
            val editor = sharedPreference.edit()
            it?.let { it1 -> editor.putLong(OWNER_ID, it1) }
            editor.commit()
        })
        Thread.sleep(50)
    }

    private fun getSharedPreference() {
        sharedPreference =
            requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        viewModel.userMobileNumber = sharedPreference.getString(MOBILE_NUMBER, null)
        viewModel.userName.value = sharedPreference.getString(USER_NAME, null)
        viewModel.userSurname.value = sharedPreference.getString(USER_SURNAME, null)
        accountType = sharedPreference.getBoolean(ACCOUNT_TYPE, true)
    }

    private fun filterAccountType() {
        if (accountType) {
            putOwnerSharedPref()
            onAddNewPet()
            profileRecyclerView.visibility = View.VISIBLE
            addNewPetButton.visibility = View.VISIBLE
            walkerInfo.visibility = View.GONE
            viewModel.getRecyclerViewData().observe(viewLifecycleOwner, Observer {
                adapt.setItems(it)
                Log.d("PetData", it.toString().plus(it.size))
                Log.d("PetData", viewModel.id.value.toString())
            })
        } else {
            viewModel.getCurrentWalker()
            profileRecyclerView.visibility = View.GONE
            allDogs.setText(R.string.profile_description)
            addNewWalkerOrderButton()
        }
    }

    private fun createRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager
        profileRecyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        adapt = PetAdapter(activity as WalkerActivity)
        profileRecyclerView.adapter = adapt
        profileRecyclerView.layoutManager = layoutManager
    }

    private fun onEditProfileClick() {
        editProfileConstraintLayout.setOnClickListener {
            viewModel.id.observe(viewLifecycleOwner, Observer {
                val editor = sharedPreference.edit()
                it?.let { it1 -> editor.putLong(ANNOUNCEMENT_ID, it1) }
                editor.putString(WALKER_DESCRIPTION, viewModel.walkerDescription)
                editor.putString(WALKER_EXPERIENCE, viewModel.walkerExperience)
                editor.commit()
            })
            (activity as WalkerActivity).onScreenStart(EditProfileFragment())
        }
    }

    private fun addNewWalkerOrderButton() {
        addWalkerNewOrder.setOnClickListener {
            viewModel.updateWalkerTables()
            profileExperience.setText(viewModel.walkerExperience)
            profileDescription.setText(viewModel.walkerDescription)
            val editor = sharedPreference.edit()
            editor.putString(WALKER_DESCRIPTION, viewModel.walkerDescription)
            editor.putString(WALKER_EXPERIENCE, viewModel.walkerExperience)
            editor.commit()
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

    private fun onExitAccountButtonClick() {
        exitAccountButton.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}
