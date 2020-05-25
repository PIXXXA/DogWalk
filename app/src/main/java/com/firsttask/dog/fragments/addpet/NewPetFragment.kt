package com.firsttask.dog.fragments.addpet

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
import com.firsttask.dog.fragments.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_new_pet.*
import javax.inject.Inject

class NewPetFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: NewPetViewModelFactory
    private lateinit var viewModel: NewPetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewPetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentNewPetBinding>(
            inflater, R.layout.fragment_new_pet, container, false
        ).run {
            lifecycleOwner = this@NewPetFragment
            viewModel = this@NewPetFragment.viewModel
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as WalkerActivity).showToolbar(R.string.new_pet_title)
        onClickRadioButton()
        continueClick()
    }

    private fun onClickRadioButton() {
        petSizeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.sizeLittle -> {
                    viewModel.petSize.value = "Little"
                }
                R.id.sizeMiddle -> {
                    viewModel.petSize.value = "Middle"
                }
                R.id.sizeBig -> {
                    viewModel.petSize.value = "Big"
                }
                else -> {
                    viewModel.petSize.value = "Little"
                    sizeLittle.isChecked = true
                }
            }
        }
    }

    private fun continueClick() {
        saveNewPetButton.setOnClickListener {
            if (viewModel.validateEditText(
                    newPetName,
                    newPetAge,
                    newPetDescription
                )
            ) {
                viewModel.addNewPetToDatabase()
                (activity as WalkerActivity).onScreenStart(ProfileFragment())
            }
        }
    }
}
