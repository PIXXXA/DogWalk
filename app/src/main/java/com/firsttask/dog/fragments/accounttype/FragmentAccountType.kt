package com.firsttask.dog.fragments.accounttype

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.firsttask.dog.R

class FragmentAccountType : Fragment() {

    companion object {
        fun newInstance() = FragmentAccountType()
    }

    private lateinit var viewModel: FragmentAccountTypeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_type, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentAccountTypeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}