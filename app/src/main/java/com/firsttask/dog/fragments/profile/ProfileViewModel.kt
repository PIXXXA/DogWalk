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

    var petItems = MutableLiveData<List<Pet>>()
    var userMobileNumber: String? = null
    var userName = MutableLiveData<String>()
    var userSurname = MutableLiveData<String>()
    var ownerName = MutableLiveData<String>()
    var ownerId: Long? = null

    fun getCurrentOwner() {
        GlobalScope.launch {
            val currentOwner = appDatabase.ownerDao()
                .getCurrentOwner(userName.value, userSurname.value, userMobileNumber)
            ownerName.postValue(currentOwner.name?.plus(" ").plus(currentOwner.surname))
            ownerId = currentOwner.ownerId
        }
    }

    fun getRecyclerViewData() {
        GlobalScope.launch {
            petItems.postValue(
                appDatabase.petDao().getAllPet(ownerId)
            )
        }
    }
}
