package com.firsttask.dog.fragments.addpet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase

class NewPetViewModelFactory (
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            ResourceProvider::class.java,
            AppDatabase::class.java
        ).newInstance(resourceProvider, appDatabase)
    }
}