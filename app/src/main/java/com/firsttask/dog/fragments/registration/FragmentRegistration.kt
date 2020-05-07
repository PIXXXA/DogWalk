package com.firsttask.dog.fragments.registration

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.Application
import com.firsttask.dog.R
import com.firsttask.dog.activity.MainActivity
import com.firsttask.dog.databinding.FragmentRegistrationBinding
import com.firsttask.dog.fragments.startscreen.FragmentStartScreen
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject


class FragmentRegistration : Fragment() {

    @Inject
    lateinit var viewModelFactory: FragmentRegistrationFactory
    private lateinit var viewModel: FragmentRegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).showToolbar(R.string.registration_title)
        Application.appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(FragmentRegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRegistrationBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.toggleButtonValidation(typeAccountButton)
        continueClick()
    }

    fun continueClick() {
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
                (activity as MainActivity).onScreenStart(FragmentStartScreen())
            }
        }
    }
}