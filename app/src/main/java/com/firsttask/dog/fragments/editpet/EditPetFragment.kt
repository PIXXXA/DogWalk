package com.firsttask.dog.fragments.editpet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.*
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentEditPetBinding
import com.firsttask.dog.fragments.order.OrderFragment
import com.firsttask.dog.fragments.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_edit_pet.*
import javax.inject.Inject

class EditPetFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: EditPetViewModelFactory
    private lateinit var viewModel: EditPetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EditPetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentEditPetBinding>(
            inflater, R.layout.fragment_edit_pet, container, false
        ).run {
            lifecycleOwner = this@EditPetFragment
            viewModel = this@EditPetFragment.viewModel
            arguments.let {
                this@EditPetFragment.viewModel.editPetName.value = it?.getString(PET_NAME)
                this@EditPetFragment.viewModel.editPetSize.value = it?.getString(PET_SIZE)
                this@EditPetFragment.viewModel.editPetAge.value = it?.getString(PET_AGE)
                this@EditPetFragment.viewModel.editPetDescription.value = it?.getString(
                    PET_DESCRIPTION
                )
            }
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as WalkerActivity).showToolbar(R.string.edit_pet_title)
        onSaveEditClick()
        onNewOrderClick()
    }

    private fun onClickRadioButton() {
        editPetSizeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.sizeLittle -> {
                    viewModel.editPetSize.value = "Little"
                }
                R.id.sizeMiddle -> {
                    viewModel.editPetSize.value = "Middle"
                }
                R.id.sizeBig -> {
                    viewModel.editPetSize.value = "Big"
                }
                else -> {
                    when (viewModel.editPetSize.value) {
                        "Little" -> {
                            sizeLittle.isChecked = true
                        }
                        "Middle" -> {
                            sizeMiddle.isChecked = true
                        }
                        else -> {
                            sizeBig.isChecked = true
                        }
                    }
                }
            }
        }
    }

    private fun onNewOrderClick() {
        addOrder.setOnClickListener {
            val fragmentB = OrderFragment()
            val bundle = Bundle()
            bundle.putString(PET_NAME, viewModel.editPetName.value)
            bundle.putString(PET_DESCRIPTION, viewModel.editPetDescription.value)
            bundle.putString(PET_SIZE, viewModel.editPetSize.value)
            bundle.putString(PET_AGE, viewModel.editPetAge.value)
            fragmentB.arguments = bundle
            (activity as WalkerActivity).onScreenStart(OrderFragment())
        }
    }

    private fun onSaveEditClick() {
        editPetSaveButton.setOnClickListener {
            if (viewModel.validateEditText(
                    editPetName,
                    editPetAge,
                    editPetDescription
                )
            ) {
                onClickRadioButton()
                viewModel.addEditedPetToDatabase()
                (activity as WalkerActivity).onScreenStart(ProfileFragment())
            }
        }
    }
}
