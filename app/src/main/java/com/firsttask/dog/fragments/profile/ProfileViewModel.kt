package com.firsttask.dog.fragments.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.Pet
import com.firsttask.dog.db.entity.Walker
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
    var name = MutableLiveData<String>()
    var walkerExperience = MutableLiveData<String>()
    var walkerDescription = MutableLiveData<String>()
    var id: Long? = null

    fun getCurrentOwner() {
        GlobalScope.launch {
            val currentOwner = appDatabase.ownerDao()
                .getCurrentOwner(userName.value, userSurname.value, userMobileNumber)
            name.postValue(currentOwner.name?.plus(" ").plus(currentOwner.surname))
            id = currentOwner.ownerId
        }
    }

    fun getRecyclerViewData() {
        GlobalScope.launch {
            petItems.postValue(
                appDatabase.petDao().getAllPet(id)
            )
        }
    }

    fun getCurrentWalker() {
        GlobalScope.launch {
            val currentWalker = appDatabase.walkersDao()
                .getCurrentWalker(userName.value, userSurname.value, userMobileNumber)
            name.postValue(currentWalker.name?.plus(" ").plus(currentWalker.surname))
            id = currentWalker.walkersId
        }
    }

    fun updateWalkerTables() {
        GlobalScope.launch {
            appDatabase.walkersDao().update(
                Walker(
                    walkersId = id,
                    name = userName.value,
                    surname = userSurname.value,
                    mobileNumber = userMobileNumber,
                    experience = walkerExperience.value,
                    description = walkerDescription.value
                )
            )
        }
    }
}
