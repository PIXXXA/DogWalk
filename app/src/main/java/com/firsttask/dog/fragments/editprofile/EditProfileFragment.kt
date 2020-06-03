package com.firsttask.dog.fragments.editprofile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.*
import com.firsttask.dog.activity.LoginActivity
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentEditProfileBinding
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import javax.inject.Inject
import kotlin.system.exitProcess

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
        viewModel.accountType = sharedPreference.getBoolean(ACCOUNT_TYPE, true)
        viewModel.id = sharedPreference.getLong(USER_ID, 0)
        viewModel.announcementId = sharedPreference.getLong(ANNOUNCEMENT_ID, 0)
        if (viewModel.accountType != true) {
            viewModel.description = sharedPreference.getString(WALKER_DESCRIPTION, null)
            viewModel.experience = sharedPreference.getString(WALKER_EXPERIENCE, null)
        }
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
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }
    }
}
