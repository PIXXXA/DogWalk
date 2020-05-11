package com.firsttask.dog.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    var loginEmail = MutableLiveData<String>()
    var loginPassword = MutableLiveData<String>()

    fun databaseValidation(loginCallback: LoginCallback) {
        GlobalScope.launch {
            var user : User? = null
            user = appDatabase.userDao()
                .getLoginValidation(loginEmail.value.toString(), loginPassword.value.toString())
            loginCallback.setData(user)
        }
    }
}