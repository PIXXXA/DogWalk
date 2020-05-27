package com.firsttask.dog.fragments.announcementlist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.ACCOUNT_TYPE
import com.firsttask.dog.Application
import com.firsttask.dog.R
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentAnnouncementBinding
import com.firsttask.dog.fragments.announcementlist.adapter.OwnerAdapter
import com.firsttask.dog.fragments.announcementlist.adapter.WalkerAdapter
import com.firsttask.dog.fragments.filter.FilterFragment
import com.firsttask.dog.fragments.searchresult.SearchFragment
import kotlinx.android.synthetic.main.fragment_announcement.*
import javax.inject.Inject

class AnnouncementFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: AnnouncementViewModelFactory
    private lateinit var viewModel: AnnouncementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AnnouncementViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentAnnouncementBinding>(
            inflater, R.layout.fragment_announcement, container, false
        ).run {
            lifecycleOwner = this@AnnouncementFragment
            viewModel = this@AnnouncementFragment.viewModel
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onItemClick()
        (activity as WalkerActivity).hideToolbar()
        val sharedPreference: SharedPreferences =
            requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        viewModel.accountType = sharedPreference.getBoolean(ACCOUNT_TYPE, true)
        filterAccountType()
    }

    private fun createRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager
        search_recycler_view.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        if (viewModel.accountType) {
            search_recycler_view.adapter = OwnerAdapter(viewModel.ownerItems.value)
        } else {
            search_recycler_view.adapter = WalkerAdapter(viewModel.walkerItems.value)
        }
        search_recycler_view.layoutManager = layoutManager
    }

    private fun filterAccountType() {
        if (viewModel.accountType) {
            viewModel.getOwnerRecyclerViewData()
            viewModel.ownerItems.observe(viewLifecycleOwner, Observer { createRecyclerView() })
        } else {
            viewModel.getWalkerRecyclerViewData()
            viewModel.walkerItems.observe(viewLifecycleOwner, Observer { createRecyclerView() })
        }
    }

    private fun onItemClick() {
        filterButton.setOnClickListener {
            (activity as WalkerActivity).onScreenStart(FilterFragment())
        }
        confirmSearchButton.setOnClickListener {
            (activity as WalkerActivity).onScreenStart(SearchFragment())
        }
    }
}
