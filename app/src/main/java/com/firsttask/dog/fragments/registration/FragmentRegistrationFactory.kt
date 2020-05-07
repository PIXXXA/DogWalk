package com.firsttask.dog.fragments.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.ResourceProvider

class FragmentRegistrationFactory(private val resourceProvider: ResourceProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            ResourceProvider::class.java
        ).newInstance(resourceProvider)
    }
}