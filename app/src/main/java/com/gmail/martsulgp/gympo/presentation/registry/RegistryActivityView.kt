package com.gmail.martsulgp.gympo.presentation.registry

import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpView

interface RegistryActivityView : MvpView {
    fun showFragment(fragment: Fragment)
}
