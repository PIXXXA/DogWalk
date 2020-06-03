package com.firsttask.dog.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.firsttask.dog.R
import com.firsttask.dog.fragments.startscreen.StartScreenFragment
import kotlinx.android.synthetic.main.fragment_toolbar.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addToolbar()
        supportFragmentManager.beginTransaction()
            .replace(R.id.loginFragmentContainer, StartScreenFragment()).commit()
    }

    fun onScreenStart(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.loginFragmentContainer, fragment).addToBackStack(null).commit()
    }

    fun onScreenStartWithoutBS(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.walkerFragmentContainer, fragment).commit()
    }

    private fun addToolbar() {
        setSupportActionBar(mainToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    fun hideToolbar() {
        supportActionBar?.hide()
    }

    fun showToolbar(stringId: Int) {
        supportActionBar?.show()
        supportActionBar?.title = getString(stringId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}