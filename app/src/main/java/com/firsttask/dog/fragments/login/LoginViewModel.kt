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
    var accountType: Boolean? = false
    var mobileNumber: String? = null
    var name: String? = null
    var surname: String? = null
    var homeAddress: String? = null

    fun databaseValidation(loginCallback: LoginCallback) {
        GlobalScope.launch {
            val user: User? = appDatabase.userDao()
                .getLoginValidation(loginEmail.value.toString(), loginPassword.value.toString())
            loginCallback.setData(user)
            accountType = user?.accountType
            mobileNumber = user?.mobileNumber
            name = user?.name
            surname = user?.surname
            homeAddress = user?.homeAddress
        }
    }
}