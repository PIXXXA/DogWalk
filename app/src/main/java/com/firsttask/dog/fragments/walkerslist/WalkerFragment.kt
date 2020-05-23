package com.firsttask.dog.fragments.walkerslist

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
import com.firsttask.dog.Application
import com.firsttask.dog.R
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentWalkerBinding
import com.firsttask.dog.db.entity.Walker
import com.firsttask.dog.fragments.filter.FilterFragment
import com.firsttask.dog.fragments.searchresult.SearchFragment
import com.firsttask.dog.fragments.walkerslist.adapter.WalkerAdapter
import kotlinx.android.synthetic.main.fragment_walker.*
import javax.inject.Inject

class WalkerFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: WalkerViewModelFactory
    private lateinit var viewModel: WalkerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WalkerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<FragmentWalkerBinding>(
            inflater, R.layout.fragment_walker, container, false
        ).run {
            lifecycleOwner = this@WalkerFragment
            viewModel = this@WalkerFragment.viewModel
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
