package com.firsttask.dog.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firsttask.dog.R
import com.firsttask.dog.fragments.login.FragmentLogin
import com.firsttask.dog.fragments.startscreen.FragmentStartScreen
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onScreenStart()
    }

    private fun onScreenStart(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, FragmentStartScreen()).commit()
    }
}
