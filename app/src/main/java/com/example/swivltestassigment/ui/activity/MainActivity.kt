package com.example.swivltestassigment.ui.activity

import android.os.Bundle
import com.example.swivltestassigment.R
import com.example.swivltestassigment.ui.fragment.home.HomeFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount != 0) {
            supportFragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
