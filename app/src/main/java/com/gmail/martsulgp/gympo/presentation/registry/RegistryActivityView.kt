package com.gmail.martsulgp.gympo.presentation.registry

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpView

interface RegistryActivityView : MvpView {
    fun showFragment(fragment: Fragment)
}
