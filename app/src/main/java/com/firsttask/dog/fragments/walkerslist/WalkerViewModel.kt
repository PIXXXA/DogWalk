package com.firsttask.dog.fragments.walkerslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WalkerViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    val exampleItems = MutableLiveData<ArrayList<User>>()
    val searchField = MutableLiveData<String>()

    fun getRecyclerViewData() {
        GlobalScope.launch {
//            exampleItems.value = appDatabase.userDao().getAllWalkers() as ArrayList
        }
    }
}