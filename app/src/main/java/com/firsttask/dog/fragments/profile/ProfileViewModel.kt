package com.firsttask.dog.fragments.profile

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.Owner
import com.firsttask.dog.db.entity.Pet
import com.firsttask.dog.db.entity.Walker
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProfileViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    var userMobileNumber: String? = null
    var userName = MutableLiveData<String>()
    var userSurname = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var walkerExperience: String? = null
    var walkerDescription: String? = null
    var id = MutableLiveData<Long?>()
    private var currentOwner: Owner? = null

    fun getCurrentOwner(): MutableLiveData<Long?> {
        runBlocking {
            GlobalScope.launch {
                currentOwner = appDatabase.ownerDao()
                    .getCurrentOwner(userName.value, userSurname.value, userMobileNumber)
                name.postValue(currentOwner?.name?.plus(" ").plus(currentOwner?.surname))
                id.postValue(currentOwner?.ownerId)
            }
        }
        return id
    }

    fun getRecyclerViewData(): LiveData<List<Pet>> {
        id.value = currentOwner?.ownerId
        Log.d("PetData", id.value.toString())
        return appDatabase.petDao().getAllPet(id.value)
    }

    fun getCurrentWalker() {
        GlobalScope.launch {
            val currentWalker = appDatabase.walkersDao()
                .getCurrentWalker(userName.value, userSurname.value, userMobileNumber)
            id.postValue(currentWalker.walkersId)
            walkerDescription = currentWalker.description
            walkerExperience = currentWalker.experience
            name.postValue(currentWalker.name?.plus(" ").plus(currentWalker.surname))
        }
    }

    fun updateWalkerTables() {
        GlobalScope.launch {
            appDatabase.walkersDao().update(
                Walker(
                    walkersId = id.value,
                    name = userName.value,
                    surname = userSurname.value,
                    mobileNumber = userMobileNumber,
                    experience = walkerExperience,
                    description = walkerDescription
                )
            )
        }
    }
}
