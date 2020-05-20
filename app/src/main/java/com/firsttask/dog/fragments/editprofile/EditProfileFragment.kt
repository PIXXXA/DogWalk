package com.firsttask.dog.fragments.editprofile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.Application

import com.firsttask.dog.R
import com.firsttask.dog.activity.LoginActivity
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentEditProfileBinding
import com.firsttask.dog.databinding.FragmentRegistrationBinding
import com.firsttask.dog.fragments.profile.ProfileFragment
import com.firsttask.dog.fragments.registration.RegistrationFactory
import com.firsttask.dog.fragments.registration.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class EditProfileFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: EditProfileFactory
    private lateinit var viewModel: EditProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EditProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentEditProfileBinding>(
            inflater, R.layout.fragment_edit_profile, container, false
        ).run {
            lifecycleOwner = this@EditProfileFragment
            viewModel = this@EditProfileFragment.viewModel
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoginActivity).showToolbar(R.string.edit_profile_title)
        continueClick()
    }

    private fun continueClick() {
        editProfileSaveButton.setOnClickListener {
            if (viewModel.validateEditText(
                    registrationName,
                    registrationSurname,
                    registrationEmail,
                    registrationPassword,
                    registrationMobileNumber,
                    registrationHomeAddress
                )
            ) {
                (activity as WalkerActivity).onScreenStart(ProfileFragment())
            }
        }
    }
}
