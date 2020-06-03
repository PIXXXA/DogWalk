package com.firsttask.dog.fragments.searchresult

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.Walker
import com.firsttask.dog.db.entity.model.OrderModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    val walkerItems = MutableLiveData<List<Walker>>()
    val ownerItems = MutableLiveData<List<OrderModel>>()
    val searchField = MutableLiveData<String>()
    val filterField = MutableLiveData<String>()
    var accountType: Boolean = true
    var argSearch: String? = null

    fun getWalkerRecyclerViewData() {
        argSearch = "%" + searchField.value + "%"
        GlobalScope.launch {
            walkerItems.postValue(appDatabase.walkersDao().searchWalker("", argSearch))
        }
    }

    fun getOwnerRecyclerViewData() {
        if(searchField.value.isNullOrEmpty()){
            argSearch = "%" + filterField.value + "%"
            GlobalScope.launch {
                ownerItems.postValue(appDatabase.orderDao().filterOrder(argSearch))
            }
        } else {
            argSearch = "%" + searchField.value + "%"
            GlobalScope.launch {
                ownerItems.postValue(appDatabase.orderDao().searchOrder(argSearch))
            }
        }
    }
}
