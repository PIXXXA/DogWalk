package com.firsttask.dog.fragments.filter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.firsttask.dog.FILTER_PET_SIZE
import com.firsttask.dog.R
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.fragments.searchresult.SearchFragment
import kotlinx.android.synthetic.main.fragment_filter.*

class FilterFragment : Fragment() {

    private var filterSize: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as WalkerActivity).showToolbar(R.string.filter_toolbar_title)
        onClickRadioGroup()
        onClickFilterButton()
    }

    private fun onClickRadioGroup() {
        dogSizeRadioButton.setOnCheckedChangeListener { _, checkedId ->
            filterSize = when (checkedId) {
                R.id.filterLittle -> {
                    resources.getString(R.string.default_pet_size_little)
                }
                R.id.filterMiddle -> {
                    resources.getString(R.string.default_pet_size_middle)
                }
                R.id.filterBig -> {
                    resources.getString(R.string.default_pet_size_big)
                }
                else -> {
                    null
                }
            }
        }
    }

    private fun onClickFilterButton() {
        filterDataButton.setOnClickListener {
            val sharedPreference: SharedPreferences =
                requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString(FILTER_PET_SIZE, filterSize)
            editor.commit()
            (activity as WalkerActivity).onScreenStart(SearchFragment())
        }
    }
}