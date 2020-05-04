package com.firsttask.dog.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firsttask.dog.R
import com.firsttask.dog.fragments.login.FragmentLogin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick() {
        supportFragmentManager.beginTransaction()
            .add(FragmentLogin(), "").commit()
    }

}
