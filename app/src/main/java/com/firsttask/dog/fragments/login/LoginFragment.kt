package com.firsttask.dog.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.Application
import com.firsttask.dog.R
import com.firsttask.dog.activity.LoginActivity
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentLoginBinding
import com.firsttask.dog.db.entity.User
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelViewModelFactory: LoginViewModelFactory
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelViewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoginActivity).showToolbar(R.string.login_title)
        onButtonClick()
    }

    private fun onButtonClick() {
        loginButton.setOnClickListener {
//            if (viewModel.databaseValidation(user) == null && viewModel.user.password == null) {
//                Toast.makeText(context, "Unregistered user, or incorrect data", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
            startActivity(
                Intent(activity, WalkerActivity::class.java)
            )
//            }
        }
    }
}
