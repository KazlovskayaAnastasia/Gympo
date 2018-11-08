package com.gmail.martsulgp.gympo.presentation.login

import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpView

interface LoginActivityView : MvpView {
    fun showFragment(fragment: Fragment)
}
