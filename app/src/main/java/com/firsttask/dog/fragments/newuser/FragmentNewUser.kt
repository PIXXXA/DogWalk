package com.firsttask.dog.fragments.newuser

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.firsttask.dog.R

class FragmentNewUser : Fragment() {

    companion object {
        fun newInstance() = FragmentNewUser()
    }

    private lateinit var viewModel: FragmentNewUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentNewUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
