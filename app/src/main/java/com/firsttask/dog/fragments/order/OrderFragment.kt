package com.firsttask.dog.fragments.order

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.Application
import com.firsttask.dog.R
import com.firsttask.dog.SECOND_PET_ID
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentOrderBinding
import com.firsttask.dog.fragments.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_order.*
import java.util.*
import javax.inject.Inject

class OrderFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: OrderViewModelFactory
    private lateinit var viewModel: OrderViewModel

    var dateAndTime: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OrderViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentOrderBinding>(
            inflater, R.layout.fragment_order, container, false
        ).run {
            lifecycleOwner = this@OrderFragment
            viewModel = this@OrderFragment.viewModel
            arguments.let {
                this@OrderFragment.viewModel.orderPetFK = it?.getLong(SECOND_PET_ID)
            }
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as WalkerActivity).showToolbar(R.string.new_pet_title)
        continueClick()
        setInitialDateTime()
        setDate()
        setTime()
    }

    private fun continueClick() {
        addNewOrder.setOnClickListener {
            if (viewModel.validateEditText(
                    orderDate
                )
            ) {
                (activity as WalkerActivity).onScreenStart(ProfileFragment())
            }
        }
    }

    // отображаем диалоговое окно для выбора даты
    private fun setDate() {
        orderDateButton.setOnClickListener {
            context?.let {
                DatePickerDialog(
                    it, d,
                    dateAndTime.get(Calendar.YEAR),
                    dateAndTime.get(Calendar.MONTH),
                    dateAndTime.get(Calendar.DAY_OF_MONTH)
                )
                    .show()
            }
        }
    }

    // отображаем диалоговое окно для выбора времени
    private fun setTime() {
        orderTimeButton.setOnClickListener {
            TimePickerDialog(
                context, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true
            )
                .show()
        }
    }

    // установка начальных даты и времени
    private fun setInitialDateTime() {
        orderDate.setText(
            DateUtils.formatDateTime(
                context,
                dateAndTime.timeInMillis,
                DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                        or DateUtils.FORMAT_SHOW_TIME
            )
        )
    }

    // установка обработчика выбора времени
    var t =
        OnTimeSetListener { _, hourOfDay, minute ->
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            dateAndTime.set(Calendar.MINUTE, minute)
            setInitialDateTime()
        }

    // установка обработчика выбора даты
    private var d =
        OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            dateAndTime.set(Calendar.YEAR, year)
            dateAndTime.set(Calendar.MONTH, monthOfYear)
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setInitialDateTime()
        }
}
