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

    var orderTime = MutableLiveData<String>()
    var orderDate = MutableLiveData<String>()

    fun validateEditText(
        orderTimeEditText: EditText,
        orderDateEditText: EditText
    ): Boolean {
        return if (orderTimeEditText.text.toString().isNotEmpty()
            && orderDateEditText.text.toString().isNotEmpty()
        ) {
            addNewOrderToDatabase()
            true
        } else {
            validateCases(orderTimeEditText)
            validateCases(orderDateEditText)
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
                    petFK = null,
                    time = orderTime.value
                )
            )
        }
    }
}
