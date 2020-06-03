package com.firsttask.dog.fragments.searchresult

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
import com.firsttask.dog.*
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentSearchBinding
import com.firsttask.dog.fragments.announcementlist.adapter.OwnerAdapter
import com.firsttask.dog.fragments.announcementlist.adapter.WalkerAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory
    private lateinit var viewModel: SearchViewModel
    lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater, R.layout.fragment_search, container, false
        ).run {
            lifecycleOwner = this@SearchFragment
            viewModel = this@SearchFragment.viewModel
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as WalkerActivity).showToolbar(R.string.search_toolbar_title)
        sharedPreference =
            requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        viewModel.accountType = sharedPreference.getBoolean(ACCOUNT_TYPE, true)
        viewModel.searchField.value = sharedPreference.getString(SEARCH_STRING, null)
        viewModel.filterField.value = sharedPreference.getString(FILTER_PET_SIZE, null)
        filterAccountType()
    }

    private fun createRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager
        search_recycler_view.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        search_recycler_view.layoutManager = layoutManager
    }

    private fun filterAccountType() {
        if (viewModel.accountType) {
            viewModel.walkerItems.observe(viewLifecycleOwner, Observer {
                search_recycler_view.adapter = viewModel.walkerItems.value?.let {
                    WalkerAdapter(
                        it,
                        activity as WalkerActivity
                    )
                }
                createRecyclerView()
            })
            viewModel.getWalkerRecyclerViewData()
        } else {
            viewModel.ownerItems.observe(viewLifecycleOwner, Observer {
                search_recycler_view.adapter =
                    viewModel.ownerItems.value?.let { OwnerAdapter(it, activity as WalkerActivity) }
                createRecyclerView()
            })
            viewModel.getOwnerRecyclerViewData()
        }
    }
}
