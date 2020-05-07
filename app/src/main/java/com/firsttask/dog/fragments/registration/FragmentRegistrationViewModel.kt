package com.firsttask.dog.fragments.registration

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ToggleButton
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.R
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FragmentRegistrationViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    var name = MutableLiveData<String>()
    var surname = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var mobileNumber = MutableLiveData<String>()
    var homeAddress = MutableLiveData<String>()

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
                }
                false -> {
                    toggleButton.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        resourceProvider.getDrawable(R.drawable.ic_pet_owner),
                        null,
                        null
                    )
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
    ) : Boolean {
        when {
             nameEditText.text.toString().isEmpty() -> {
                nameEditText.error = resourceProvider.getString(
                    R.string.registration_error_message,
                    nameEditText.hint.toString()
                )
                return false
            }
            surnameEditText.text.toString().isEmpty() -> {
                nameEditText.error = resourceProvider.getString(
                    R.string.registration_error_message,
                    nameEditText.hint.toString()
                )
                return false
            }
            emailEditText.text.toString().isEmpty() -> {
                nameEditText.error = resourceProvider.getString(
                    R.string.registration_error_message,
                    nameEditText.hint.toString()
                )
                return false
            }
            passwordEditText.text.toString().isEmpty() -> {
                nameEditText.error = resourceProvider.getString(
                    R.string.registration_error_message,
                    nameEditText.hint.toString()
                )
                return false
            }
            mobileNumberEditText.text.toString().isEmpty() -> {
                nameEditText.error = resourceProvider.getString(
                    R.string.registration_error_message,
                    nameEditText.hint.toString()
                )
                return false
            }
            homeAddressEditText.text.toString().isEmpty() -> {
                nameEditText.error = resourceProvider.getString(
                    R.string.registration_error_message,
                    nameEditText.hint.toString()
                )
                return false
            }
            else -> {
                addUserToDatabase()
                return true
            }
        }
    }

    fun validateCases(){

    }

    private fun addUserToDatabase() {
        GlobalScope.launch {
            mobileNumber.value?.toLong()?.let {
                User(
                    userId = it,
                    name = name.value,
                    surname = surname.value,
                    email = email.value,
                    password = password.value,
                    mobileNumber = mobileNumber.value,
                    homeAddress = homeAddress.value
                )
            }?.let {
                appDatabase.userDao().insert(
                    it
                )
            }
        }
    }
}
