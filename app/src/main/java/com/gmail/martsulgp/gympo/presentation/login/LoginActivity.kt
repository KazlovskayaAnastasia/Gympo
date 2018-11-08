package com.gmail.martsulgp.gympo.presentation.login

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.gmail.martsulgp.gympo.R


class LoginActivity: MvpAppCompatActivity(), LoginActivityView {

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.entryContainer, fragment).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        showFragment(LoginFragment())
    }

    companion object {
        const val TAG = "LoginActivityTag"
    }
}