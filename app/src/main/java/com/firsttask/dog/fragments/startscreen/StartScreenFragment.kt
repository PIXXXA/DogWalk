package com.firsttask.dog.fragments.startscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firsttask.dog.R
import com.firsttask.dog.activity.LoginActivity
import com.firsttask.dog.fragments.login.LoginFragment
import com.firsttask.dog.fragments.registration.RegistrationFragment
import kotlinx.android.synthetic.main.fragment_start_screen.*

class StartScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as LoginActivity).hideToolbar()
        return inflater.inflate(R.layout.fragment_start_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onButtonClick()
    }

    private fun onButtonClick() {
        loginButton.setOnClickListener {
            (activity as LoginActivity).onScreenStart(LoginFragment())
        }
        registrationButton.setOnClickListener {
            (activity as LoginActivity).onScreenStart(RegistrationFragment())
        }
    }
}
