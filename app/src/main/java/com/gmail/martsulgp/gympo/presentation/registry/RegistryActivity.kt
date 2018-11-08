package com.gmail.martsulgp.gympo.presentation.registry

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpActivity
import com.gmail.martsulgp.gympo.R


class RegistryActivity : MvpActivity(), RegistryActivityView {

    override fun showFragment(fragment: Fragment) {
//        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.entryContainer, fragment).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.entryContainer, RegistryFragment()).commit()
//        showFragment(RegistryFragment())
    }

    companion object {
        const val TAG = "RegistryActivityTag"
    }
}