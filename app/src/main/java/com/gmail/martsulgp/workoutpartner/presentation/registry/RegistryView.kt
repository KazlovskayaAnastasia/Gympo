package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.view.View
import com.arellomobile.mvp.MvpView
import com.gmail.martsulgp.workoutpartner.presentation.MainPresenter
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult

interface RegistryView : MvpView {

    //fun progressBarVisibility(b: Boolean)
    fun signIn()
    fun progressBarVisibility(b: Boolean)
    fun onConnectionFailed(connectionResult: ConnectionResult)
    fun handleSignInResult(result: GoogleSignInResult)
    fun logger(message : String, debugLevel : RegistryPresenter.DebugLevel)
}
