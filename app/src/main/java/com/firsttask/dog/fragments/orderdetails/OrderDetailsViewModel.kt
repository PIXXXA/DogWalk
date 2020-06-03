package com.firsttask.dog.fragments.orderdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase

class OrderDetailsViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    var accountType: Boolean? = true

    var name = MutableLiveData<String>()
    var surname = MutableLiveData<String>()
    var mobileNumber = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var experience = MutableLiveData<String>()

    var orderDate = MutableLiveData<String>()
    var petName = MutableLiveData<String>()
    var petAge = MutableLiveData<String>()
    var petDescription = MutableLiveData<String>()
    var petSize = MutableLiveData<String>()
    var ownerName = MutableLiveData<String>()
    var ownerSurname = MutableLiveData<String>()
    var ownerMobileNumber = MutableLiveData<String>()
}