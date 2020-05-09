package com.firsttask.dog.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.firsttask.dog.R
import com.firsttask.dog.fragments.walkerlist.WalkerFragment
import kotlinx.android.synthetic.main.fragment_toolbar.*

class WalkerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walker)
        addToolbar()
        supportFragmentManager.beginTransaction()
            .replace(R.id.loginFragmentContainer, WalkerFragment()).commit()
    }

    fun onScreenStart(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.loginFragmentContainer, fragment).addToBackStack(null).commit()
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
