package com.firsttask.dog.fragments.startscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firsttask.dog.R
import com.firsttask.dog.activity.MainActivity
import com.firsttask.dog.fragments.login.FragmentLogin
import com.firsttask.dog.fragments.registration.FragmentRegistration
import kotlinx.android.synthetic.main.fragment_start_screen.*

class FragmentStartScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).hideToolbar()
        return inflater.inflate(R.layout.fragment_start_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onButtonClick()
    }

    private fun onButtonClick() {
        loginButton.setOnClickListener {
            (activity as MainActivity).onScreenStart(FragmentLogin())
        }
        registrationButton.setOnClickListener {
            (activity as MainActivity).onScreenStart(FragmentRegistration())
        }
    }
}
