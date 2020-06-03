package com.firsttask.dog.fragments.order

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firsttask.dog.R
import com.firsttask.dog.ResourceProvider
import com.firsttask.dog.db.database.AppDatabase
import com.firsttask.dog.db.entity.Order
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrderViewModel(
    private val resourceProvider: ResourceProvider,
    private val appDatabase: AppDatabase
) : ViewModel() {

    var orderDate = MutableLiveData<String>()
    var orderPetFK: Long? = null
    var orderId = MutableLiveData<Long?>()

    fun getCurrentOrder() {
        GlobalScope.launch {
            val currentOrder = appDatabase.orderDao().getCurrentOrder(
                orderPetFK,
                " "
            )
            if (currentOrder != null){
                orderId.postValue(currentOrder.orderId)
                orderDate.postValue(currentOrder.date)
            } else {
                orderId.postValue(null)
                orderDate.postValue(null)
            }
        }
    }

    fun validateEditText(
        orderTimeEditText: EditText
    ): Boolean {
        return if (orderTimeEditText.text.toString().isNotEmpty()
        ) {
            if (orderId.value == null) {
                addNewOrderToDatabase()
            } else {
                updateOrderInDatabase()
            }
            true
        } else {
            validateCases(orderTimeEditText)
            false
        }
    }

    private fun validateCases(editText: EditText) {
        if (editText.text.toString().isEmpty()) {
            editText.error = resourceProvider.getString(
                R.string.registration_error_message,
                editText.hint.toString()
            )
        }
    }

    private fun addNewOrderToDatabase() {
        GlobalScope.launch {
            appDatabase.orderDao().insert(
                Order(
                    date = orderDate.value,
                    orderId = null,
                    petFK = orderPetFK
                )
            )
        }
    }

    private fun updateOrderInDatabase() {
        GlobalScope.launch {
            appDatabase.orderDao().update(
                Order(
                    date = orderDate.value,
                    orderId = orderId.value,
                    petFK = orderPetFK
                )
            )
        }
    }

    fun deleteCurrentOrderInDatabase() {
        GlobalScope.launch {
            appDatabase.orderDao().delete(
                Order(
                    date = orderDate.value,
                    orderId = orderId.value,
                    petFK = orderPetFK
                )
            )
        }
    }
}