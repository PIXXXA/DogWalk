package com.firsttask.dog.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.firsttask.dog.R
import com.firsttask.dog.fragments.announcementlist.AnnouncementFragment
import com.firsttask.dog.fragments.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_walker.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class WalkerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walker)
        addToolbar()
        walkerBottomNavigationView.menu.findItem(R.id.myProfile).isChecked = true
        walkerBottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelected)
        supportFragmentManager.beginTransaction()
            .replace(R.id.walkerFragmentContainer, ProfileFragment()).commit()
    }

    fun onScreenStart(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.walkerFragmentContainer, fragment).addToBackStack(null).commit()
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

    private val onNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.allWalker -> {
                onScreenStartWithoutBS(AnnouncementFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.myProfile -> {
                onScreenStartWithoutBS(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }
}