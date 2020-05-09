package com.firsttask.dog.fragments.walkerlist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.firsttask.dog.R

class WalkerFragment : Fragment() {

    companion object {
        fun newInstance() = WalkerFragment()
    }

    private lateinit var viewModel: WalkerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_walker, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WalkerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
