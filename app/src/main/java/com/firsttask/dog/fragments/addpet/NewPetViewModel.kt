package com.firsttask.dog.fragments.addpet

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.R
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.Pet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewPetViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    var petName = MutableLiveData<String>()
    var petAge = MutableLiveData<String>()
    var petDescription = MutableLiveData<String>()
    var petSize = MutableLiveData<String>()

    fun validateEditText(
        nameEditText: EditText,
        ageEditText: EditText,
        descriptionEditText: EditText
    ): Boolean {
        return if (nameEditText.text.toString().isNotEmpty()
            && ageEditText.text.toString().isNotEmpty()
            && descriptionEditText.text.toString().isNotEmpty()
        ) {
            true
        } else {
            validateCases(nameEditText)
            validateCases(ageEditText)
            validateCases(descriptionEditText)
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

    fun addNewPetToDatabase() {
        GlobalScope.launch {
            appDatabase.petDao().insert(
                Pet(
                    petId = null,
                    ownerFK = null,
                    name = petName.value,
                    age = petAge.value,
                    description = petDescription.value,
                    size = petSize.value
                )
            )
        }
    }
}
