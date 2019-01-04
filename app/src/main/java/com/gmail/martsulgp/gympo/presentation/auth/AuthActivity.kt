package com.gmail.martsulgp.gympo.presentation.auth

import android.os.Bundle
import com.arellomobile.mvp.MvpActivity
import com.gmail.martsulgp.gympo.R
import com.gmail.martsulgp.gympo.presentation.auth.login.LoginFragment


class AuthActivity: MvpActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.entryContainer, LoginFragment.newInstance("")).commit()
    }

    companion object {
        const val TAG = "AuthActivityTag"
    }
}