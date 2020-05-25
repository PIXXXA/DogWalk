package com.firsttask.dog.fragments.announcementlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.Application
import com.firsttask.dog.R
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentWalkerBinding
import com.firsttask.dog.fragments.filter.FilterFragment
import com.firsttask.dog.fragments.searchresult.SearchFragment
import kotlinx.android.synthetic.main.fragment_walker.*
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
        return DataBindingUtil.inflate<FragmentWalkerBinding>(
            inflater, R.layout.fragment_walker, container, false
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
//        viewModel.exampleItems.observe(viewLifecycleOwner, Observer { createRecyclerView(it) })
    }

//    private fun createRecyclerView(arrayList: ArrayList<WalkerModel>) {
//        viewModel.getRecyclerViewData()
//        val layoutManager: RecyclerView.LayoutManager
//        search_recycler_view.setHasFixedSize(true)
//        layoutManager = LinearLayoutManager(activity)
//        val adapt = WalkerAdapter(arrayList)
//        search_recycler_view.adapter = adapt
//        search_recycler_view.layoutManager = layoutManager
//    }

    private fun onItemClick() {
        filterButton.setOnClickListener {
            (activity as WalkerActivity).onScreenStart(FilterFragment())
        }
        confirmSearchButton.setOnClickListener {
            (activity as WalkerActivity).onScreenStart(SearchFragment())
        }
    }
}
