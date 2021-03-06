package com.firsttask.dog.fragments.registration

import android.widget.EditText
import android.widget.ToggleButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.R
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.Owner
import com.firsttask.dog.db.entity.User
import com.firsttask.dog.db.entity.Walker
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RegistrationViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    var name = MutableLiveData<String>()
    var surname = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var mobileNumber = MutableLiveData<String>()
    var homeAddress = MutableLiveData<String>()
    private var accountType: Boolean? = true

    fun toggleButtonValidation(toggleButton: ToggleButton) {
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    toggleButton.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        resourceProvider.getDrawable(R.drawable.ic_walking_the_dog),
                        null,
                        null
                    )
                    accountType = false
                }
                false -> {
                    toggleButton.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        resourceProvider.getDrawable(R.drawable.ic_pet_owner),
                        null,
                        null
                    )
                    accountType = true
                }
            }
        }
    }

    fun validateEditText(
        nameEditText: EditText,
        surnameEditText: EditText,
        emailEditText: EditText,
        passwordEditText: EditText,
        mobileNumberEditText: EditText,
        homeAddressEditText: EditText
    ): Boolean {
        return if (nameEditText.text.toString().isNotEmpty() && surnameEditText.text.toString()
                .isNotEmpty() && emailEditText.text.toString()
                .isNotEmpty() && passwordEditText.text.toString()
                .isNotEmpty() && mobileNumberEditText.text.toString()
                .isNotEmpty() && homeAddressEditText.text.toString().isNotEmpty()
        ) {
            addUserToDatabase()
            true
        } else {
            validateCases(nameEditText)
            validateCases(surnameEditText)
            validateCases(emailEditText)
            validateCases(passwordEditText)
            validateCases(mobileNumberEditText)
            validateCases(homeAddressEditText)
            false
        }
    }

    private fun validateCases(editText: EditText) {
        if (editText.text.toString().isEmpty()) {
            editText.error = resourceProvider.getString(
                R.string.registration_error_message,
                editText.hint.toString()
            )
        }
    }

    private fun addUserToDatabase() {
        GlobalScope.launch {
            appDatabase.userDao().insert(
                User(
                    userId = null,
                    name = name.value,
                    surname = surname.value,
                    email = email.value,
                    password = password.value,
                    mobileNumber = mobileNumber.value,
                    homeAddress = homeAddress.value,
                    accountType = accountType
                )
            )
            filterAddedUsers()
        }
    }

    private fun filterAddedUsers() {
        if (accountType!!) {
            GlobalScope.launch {
                appDatabase.ownerDao().insert(
                    Owner(
                        ownerId = null,
                        name = name.value,
                        surname = surname.value,
                        mobileNumber = mobileNumber.value
                    )
                )
            }
        } else {
            GlobalScope.launch {
                appDatabase.walkersDao().insert(
                    Walker(
                        walkersId = null,
                        name = name.value,
                        surname = surname.value,
                        mobileNumber = mobileNumber.value,
                        description = null,
                        experience = null
                    )
                )
            }
        }
    }
}