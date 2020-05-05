package com.firsttask.dog.fragments.startscreen

import android.R.attr.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.firsttask.dog.R
import com.firsttask.dog.fragments.login.FragmentLogin
import kotlinx.android.synthetic.main.fragment_start_screen.*


class FragmentStartScreen : Fragment() {

    private lateinit var viewModel: FragmentStartScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentStartScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startLogin
    }

    var startLogin = loginButton.setOnClickListener(View.OnClickListener {
        childFragmentManager.beginTransaction().add(FragmentLogin(), "").commit()
    })

    companion object {
        fun newInstance() = FragmentStartScreen()
    }
}
