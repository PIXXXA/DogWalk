package com.firsttask.dog.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.R

class FragmentLogin : Fragment() {

    private lateinit var viewModel: FragmentLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentLoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        fun newInstance() = FragmentLogin()
    }

}
