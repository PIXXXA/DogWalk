package com.firsttask.dog.fragments.registration

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.Application
import com.firsttask.dog.R
import com.firsttask.dog.activity.MainActivity
import com.firsttask.dog.fragments.startscreen.FragmentStartScreen
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject


class FragmentRegistration : Fragment() {

    @Inject
    lateinit var viewModelFactory: FragmentRegistrationFactory
    private lateinit var viewModel: FragmentRegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(FragmentRegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.toggleButtonValidation(typeAccountButton)
//        onClickItem()
    }

//    private fun onClickItem(){
//        toolbar.setOnClickListener{
//            when(toolbar.id){
//                R.id.action_back-> (activity as MainActivity).onScreenStart(FragmentStartScreen())
//            }
//        }
//    }
}