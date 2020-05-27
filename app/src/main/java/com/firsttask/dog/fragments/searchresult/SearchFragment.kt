package com.firsttask.dog.fragments.searchresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firsttask.dog.Application
import com.firsttask.dog.R
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.db.entity.Walker
import com.firsttask.dog.fragments.announcementlist.adapter.WalkerAdapter
import kotlinx.android.synthetic.main.fragment_announcement.*
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as WalkerActivity).showToolbar(R.string.search_toolbar_title)
        viewModel.exampleItems.observe(viewLifecycleOwner, Observer { createRecyclerView(it) })
    }

    private fun createRecyclerView(arrayList: ArrayList<Walker>) {
        viewModel.getRecyclerViewData()
        val layoutManager: RecyclerView.LayoutManager
        search_recycler_view.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        val adapt = WalkerAdapter(arrayList)
        search_recycler_view.adapter = adapt
        search_recycler_view.layoutManager = layoutManager
    }
}
