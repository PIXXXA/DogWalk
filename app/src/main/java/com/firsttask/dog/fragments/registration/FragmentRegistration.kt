package com.firsttask.dog.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.R

class FragmentRegistration : Fragment() {

    private lateinit var viewModel: FragmentRegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentRegistrationViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = FragmentRegistration()
    }

}
