package com.firsttask.dog.fragments.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firsttask.dog.*
import com.firsttask.dog.activity.LoginActivity
import com.firsttask.dog.activity.WalkerActivity
import com.firsttask.dog.databinding.FragmentLoginBinding
import com.firsttask.dog.db.entity.User
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment(), LoginCallback {

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
        return DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login, container, false
        ).run {
            lifecycleOwner = this@LoginFragment
            viewModel = this@LoginFragment.viewModel
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LoginActivity).showToolbar(R.string.login_title)
        loginButton.setOnClickListener { viewModel.databaseValidation(this) }
    }

    private fun onButtonClick(user: User?) {
        if (user == null) {
            requireActivity().runOnUiThread {
                Toast.makeText(context, "Unregistered user, or incorrect data", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            val sharedPreference: SharedPreferences =
                requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString(USER_PASSWORD, viewModel.loginPassword.value)
            editor.putString(USER_EMAIL, viewModel.loginEmail.value)
            editor.putString(MOBILE_NUMBER, viewModel.mobileNumber)
            editor.putString(MOBILE_NUMBER, viewModel.mobileNumber)
            editor.putString(MOBILE_NUMBER, viewModel.mobileNumber)
            editor.putString(MOBILE_NUMBER, viewModel.mobileNumber)
            viewModel.accountType?.let { editor.putBoolean(ACCOUNT_TYPE, it) }
            editor.commit()
            val intent = Intent(activity, WalkerActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    override fun setData(user: User?) {
        onButtonClick(user)
    }
}
