package com.firsttask.dog.fragments.startscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.ResourceProvider
import javax.inject.Inject

class FragmentStartScreenFactory @Inject constructor(private val resourceProvider: ResourceProvider) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            ResourceProvider::class.java
        ).newInstance(resourceProvider)
    }
}