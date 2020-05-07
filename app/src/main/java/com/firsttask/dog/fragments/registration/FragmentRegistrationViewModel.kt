package com.firsttask.dog.fragments.registration

import android.widget.ToggleButton
import androidx.lifecycle.ViewModel
import com.firsttask.dog.R
import com.firsttask.dog.ResourceProvider

class FragmentRegistrationViewModel(private val resourceProvider: ResourceProvider) : ViewModel() {

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
}
