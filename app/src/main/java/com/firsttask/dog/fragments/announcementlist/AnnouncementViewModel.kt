package com.firsttask.dog.fragments.announcementlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.Walker
import com.firsttask.dog.db.entity.model.OrderModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Entity

class AnnouncementViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    val walkerItems = MutableLiveData<List<Walker>>()
    val ownerItems = MutableLiveData<List<OrderModel>>()
    val searchField = MutableLiveData<String>()
    var entity: Entity? = null
    var accountType: Boolean = true

    fun getWalkerRecyclerViewData() {
        GlobalScope.launch {
            walkerItems.postValue(appDatabase.walkersDao().getWalker())
        }
    }

    fun getOwnerRecyclerViewData() {
        GlobalScope.launch {
            ownerItems.postValue(appDatabase.orderDao().getOrder())
        }
    }
}