package com.firsttask.dog.fragments.editprofile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.*
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentEditProfileBinding
import com.firsttask.dog.fragments.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_edit_profile.*
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
        setStartFields()
        super.onViewCreated(view, savedInstanceState)
        (activity as WalkerActivity).showToolbar(R.string.edit_profile_title)
        continueClick()
    }

    private fun setStartFields() {
        val sharedPreference: SharedPreferences =
            requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        viewModel.name.value = sharedPreference.getString(USER_NAME, null)
        viewModel.surname.value = sharedPreference.getString(USER_SURNAME, null)
        viewModel.email.value = sharedPreference.getString(USER_EMAIL, null)
        viewModel.password.value = sharedPreference.getString(USER_PASSWORD, null)
        viewModel.mobileNumber.value = sharedPreference.getString(MOBILE_NUMBER, null)
        viewModel.homeAddress.value = sharedPreference.getString(USER_ADDRESS, null)
    }

    private fun continueClick() {
        editProfileSaveButton.setOnClickListener {
            if (viewModel.validateEditText(
                    editProfileName,
                    editProfileSurname,
                    editProfileEmail,
                    editProfilePassword,
                    editProfileMobileNumber,
                    editProfileHomeAddress
                )
            ) {
                (activity as WalkerActivity).onScreenStart(ProfileFragment())
            }
        }
    }
}
