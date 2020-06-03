package com.firsttask.dog.fragments.orderdetails

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.*
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentOrderDetailsBinding
import com.firsttask.dog.fragments.profile.ProfileViewModelFactory
import kotlinx.android.synthetic.main.fragment_order_details.*
import javax.inject.Inject

class OrderDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ProfileViewModelFactory
    private lateinit var viewModel: OrderDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(OrderDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPreference: SharedPreferences =
            requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        viewModel.accountType = sharedPreference.getBoolean(ACCOUNT_TYPE, true)
        return DataBindingUtil.inflate<FragmentOrderDetailsBinding>(
            inflater, R.layout.fragment_order_details, container, false
        ).run {
            lifecycleOwner = this@OrderDetailsFragment
            viewModel = this@OrderDetailsFragment.viewModel
            arguments.let {
                this@OrderDetailsFragment.viewModel.orderDate.value = it?.getString(OWNER_DATE)
                this@OrderDetailsFragment.viewModel.petName.value =
                    it?.getString(OWNER_PET_NAME)
                this@OrderDetailsFragment.viewModel.petAge.value = it?.getString(OWNER_PET_AGE)
                this@OrderDetailsFragment.viewModel.petDescription.value =
                    it?.getString(OWNER_PET_DESCRIPTION)
                this@OrderDetailsFragment.viewModel.petSize.value =
                    it?.getString(OWNER_PET_SIZE)
                this@OrderDetailsFragment.viewModel.ownerName.value = it?.getString(OWNER_NAME)
                this@OrderDetailsFragment.viewModel.ownerSurname.value =
                    it?.getString(OWNER_SURNAME)
                this@OrderDetailsFragment.viewModel.ownerMobileNumber.value =
                    it?.getString(OWNER_MOBILE_NUMBER)

                this@OrderDetailsFragment.viewModel.name.value = it?.getString(WALKER_NAME)
                this@OrderDetailsFragment.viewModel.surname.value =
                    it?.getString(WALKER_SURNAME)
                this@OrderDetailsFragment.viewModel.mobileNumber.value =
                    it?.getString(WALKER_MOBILE_NUMBER)
                this@OrderDetailsFragment.viewModel.experience.value =
                    it?.getString(WALKER_EXPERIENCE)
                this@OrderDetailsFragment.viewModel.description.value =
                    it?.getString(WALKER_DESCRIPTION)
            }
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as WalkerActivity).showToolbar(R.string.order_details_title)
        setFilteredText()
    }

    private fun setFilteredText() {
        if (viewModel.accountType == true) {
            orderDetailsOwnerName.text = resources.getString(
                R.string.order_details_name,
                viewModel.name.value.plus(" ").plus(viewModel.surname.value)
            )
            orderDetailsOwnerMobileNumber.text = resources.getString(
                R.string.order_details_mobile_number,
                viewModel.mobileNumber.value
            )
            orderDetailsDate.text =
                resources.getString(R.string.order_details_description, viewModel.description.value)
            orderDetailsPetName.text =
                resources.getString(R.string.order_details_experience, viewModel.experience.value)
        } else {
            orderDetailsPetAge.visibility = View.VISIBLE
            orderDetailsPetDescription.visibility = View.VISIBLE
            orderDetailsPetSize.visibility = View.VISIBLE
            orderDetailsOwnerName.text = resources.getString(
                R.string.order_details_owner_name,
                viewModel.ownerName.value.plus(" ").plus(viewModel.ownerSurname.value)
            )
            orderDetailsOwnerMobileNumber.text = resources.getString(
                R.string.order_details_owner_mobile_number,
                viewModel.ownerMobileNumber.value
            )
            orderDetailsDate.text =
                resources.getString(R.string.order_details_date, viewModel.orderDate.value)
            orderDetailsPetName.text =
                resources.getString(R.string.order_details_pet_name, viewModel.petName.value)
            orderDetailsPetAge.text =
                resources.getString(R.string.order_details_pet_age, viewModel.petAge.value)
            orderDetailsPetDescription.text = resources.getString(
                R.string.order_details_pet_description,
                viewModel.petDescription.value
            )
            orderDetailsPetSize.text =
                resources.getString(R.string.order_details_pet_size, viewModel.petSize.value)
        }
    }

}