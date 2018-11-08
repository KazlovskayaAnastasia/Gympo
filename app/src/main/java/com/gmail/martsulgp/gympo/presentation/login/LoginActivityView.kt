package com.gmail.martsulgp.gympo.presentation.login

import com.arellomobile.mvp.MvpView

interface LoginActivityView : MvpView {
    fun showFragment(fragment: androidx.fragment.app.Fragment)
}
