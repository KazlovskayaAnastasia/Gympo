package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpView
import com.gmail.martsulgp.workoutpartner.presentation.MainPresenter
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult

interface RegistryActivityView : MvpView {
    fun showFragment(fragment: Fragment)
}
