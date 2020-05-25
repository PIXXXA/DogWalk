package com.firsttask.dog.fragments.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.Application
import com.firsttask.dog.R
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentNewPetBinding
import com.firsttask.dog.databinding.FragmentOrderBinding
import com.firsttask.dog.fragments.addpet.NewPetViewModelFactory
import com.firsttask.dog.fragments.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_order.*
import javax.inject.Inject

class OrderFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: OrderViewModelFactory
    private lateinit var viewModel: OrderViewModel

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
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as WalkerActivity).showToolbar(R.string.new_pet_title)
        continueClick()
    }

    private fun continueClick() {
        addNewOrder.setOnClickListener {
            if (viewModel.validateEditText(
                    orderTime,
                    orderDate
                )
            ) {
                (activity as WalkerActivity).onScreenStart(ProfileFragment())
            }
        }
    }
}
