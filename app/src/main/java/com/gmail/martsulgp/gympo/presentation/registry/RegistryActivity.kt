package com.gmail.martsulgp.gympo.presentation.registry

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.gmail.martsulgp.gympo.R



class RegistryActivity : MvpAppCompatActivity(), RegistryActivityView {

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.entryContainer, fragment).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        showFragment(RegistryFragment())
    }

    companion object {
        const val TAG = "RegistryActivityTag"
    }
}