package com.gmail.martsulgp.workoutpartner.presentation.login

import com.arellomobile.mvp.MvpView
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult

interface LoginView : MvpView {

    fun progressBarVisibility(b: Boolean)
    fun signIn()
    fun onConnectionFailed(connectionResult: ConnectionResult)
    fun handleSignInResult(result: GoogleSignInResult)
}
