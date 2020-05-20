package com.firsttask.dog.fragments.registration

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.Application
import com.firsttask.dog.R
import com.firsttask.dog.activity.LoginActivity
import com.firsttask.dog.databinding.FragmentRegistrationBinding
import com.firsttask.dog.fragments.startscreen.StartScreenFragment
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: RegistrationFactory
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentRegistrationBinding>(
            inflater, R.layout.fragment_registration, container, false
        ).run {
            lifecycleOwner = this@RegistrationFragment
            viewModel = this@RegistrationFragment.viewModel
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoginActivity).showToolbar(R.string.registration_title)
        viewModel.toggleButtonValidation(typeAccountButton)
        continueClick()
    }

    private fun continueClick() {
        registrationContinueButton.setOnClickListener {
            if (viewModel.validateEditText(
                    registrationName,
                    registrationSurname,
                    registrationEmail,
                    registrationPassword,
                    registrationMobileNumber,
                    registrationHomeAddress
                )
            ) {
                (activity as LoginActivity).onScreenStart(StartScreenFragment())
            }
        }
    }
}