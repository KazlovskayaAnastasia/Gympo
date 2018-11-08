package com.gmail.martsulgp.gympo.presentation.login

import android.os.Bundle
import com.arellomobile.mvp.MvpActivity
import com.gmail.martsulgp.gympo.R


class LoginActivity: MvpActivity(), LoginActivityView {

    override fun showFragment(fragment: androidx.fragment.app.Fragment) {
//        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.entryContainer, fragment).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.entryContainer, LoginFragment()).commit()
//        showFragment(LoginFragment())
    }

    companion object {
        const val TAG = "LoginActivityTag"
    }
}