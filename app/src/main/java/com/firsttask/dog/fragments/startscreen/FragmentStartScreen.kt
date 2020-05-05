package com.firsttask.dog.fragments.startscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.Application
import com.firsttask.dog.R
import com.firsttask.dog.activity.MainActivity
import com.firsttask.dog.fragments.login.FragmentLogin
import com.firsttask.dog.fragments.registration.FragmentRegistration
import kotlinx.android.synthetic.main.fragment_start_screen.*
import javax.inject.Inject

class FragmentStartScreen : Fragment() {

    @Inject
    lateinit var viewModelFactory: FragmentStartScreenFactory
    private lateinit var viewModel: FragmentStartScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(FragmentStartScreenViewModel::class.java)
        onButtonClick()
    }

    private fun onButtonClick(){
        loginButton.setOnClickListener {
            (activity as MainActivity).onScreenStart(FragmentLogin())
        }
        registrationButton.setOnClickListener {
            (activity as MainActivity).onScreenStart(FragmentRegistration())
        }
    }
}
