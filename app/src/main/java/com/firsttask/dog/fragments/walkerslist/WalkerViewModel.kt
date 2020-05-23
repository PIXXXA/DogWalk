package com.firsttask.dog.fragments.walkerslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.Owner
import com.firsttask.dog.db.entity.Walker
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Entity

class WalkerViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

//    val walkerItems = MutableLiveData<ArrayList<Walker>>()
//    val ownerItems = MutableLiveData<ArrayList<Owner>>()
    val searchField = MutableLiveData<String>()
    var entity : Entity ?= null

    fun getRecyclerViewData() {
        GlobalScope.launch {
//            walkerItems.value = appDatabase.walkersDao().getWalker() as ArrayList<Walker>
//            ownerItems.value = appDatabase.ownerDao(). as ArrayList<Owner>
        }
    }
}