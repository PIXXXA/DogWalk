package com.firsttask.dog.fragments.registration

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.firsttask.dog.R

class FragmentRegistration : Fragment() {

    companion object {
        fun newInstance() = FragmentRegistration()
    }

    private lateinit var viewModel: FragmentRegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentRegistrationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
