package com.firsttask.dog.fragments.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.Pet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    val petItems = MutableLiveData<ArrayList<Pet>>()
    var userMobileNumber: String? = null
    var userName = MutableLiveData<String>()

    fun getRecyclerViewData() {
        GlobalScope.launch {
            petItems.value = appDatabase.petDao().getAllPet(userMobileNumber) as ArrayList<Pet>
        }
    }
}
