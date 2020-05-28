package com.firsttask.dog.fragments.editprofile

import android.widget.EditText
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

class EditProfileViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    var name = MutableLiveData<String>()
    var id: Long? = null
    var announcementId: Long? = null
    var surname = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var mobileNumber = MutableLiveData<String>()
    var homeAddress = MutableLiveData<String>()
    var experience : String? = null
    var description : String? = null
    var accountType: Boolean? = true

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
            editUserInDatabase()
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
                R.string.profile_error_message,
                editText.hint.toString()
            )
        }
    }

    private fun editUserInDatabase() {
        GlobalScope.launch {
            appDatabase.userDao().update(
                User(
                    userId = id,
                    name = name.value,
                    surname = surname.value,
                    email = email.value,
                    password = password.value,
                    mobileNumber = mobileNumber.value,
                    homeAddress = homeAddress.value,
                    accountType = accountType
                )
            )
            if (accountType == true) {
                updateOwnerTables()
            } else {
                updateWalkerTables()
            }
        }
    }

    private fun updateWalkerTables() {
        GlobalScope.launch {
            appDatabase.walkersDao().update(
                Walker(
                    walkersId = announcementId,
                    name = name.value,
                    surname = surname.value,
                    mobileNumber = mobileNumber.value,
                    experience = experience,
                    description = description
                )
            )
        }
    }

    private fun updateOwnerTables() {
        GlobalScope.launch {
            appDatabase.ownerDao().update(
                Owner(
                    ownerId = announcementId,
                    name = name.value,
                    surname = surname.value,
                    mobileNumber = mobileNumber.value
                )
            )
        }
    }

}