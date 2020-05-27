package com.firsttask.dog.fragments.editpet

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.R
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.Pet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditPetViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    var editPetName = MutableLiveData<String>()
    var editPetAge = MutableLiveData<String>()
    var editPetId : Long? = null
    var editOwnerId : Long? = null
    var editPetDescription = MutableLiveData<String>()
    var editPetSize = MutableLiveData<String>()

    fun validateEditText(
        nameEditText: EditText,
        ageEditText: EditText,
        descriptionEditText: EditText
    ): Boolean {
        return if (nameEditText.text.toString().isNotEmpty() && ageEditText.text.toString()
                .isNotEmpty() && descriptionEditText.text.toString()
                .isNotEmpty()
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
                R.string.pet_error_message,
                editText.hint.toString()
            )
        }
    }

    fun addEditedPetToDatabase() {
        GlobalScope.launch {
            appDatabase.petDao().update(
                Pet(
                    name = editPetName.value,
                    age = editPetAge.value,
                    description = editPetDescription.value,
                    size = editPetSize.value,
                    ownerFK = editOwnerId,
                    petId = editPetId
                )
            )
        }
    }
}