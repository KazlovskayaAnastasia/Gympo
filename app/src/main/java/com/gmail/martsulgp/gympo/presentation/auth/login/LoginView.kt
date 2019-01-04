package com.gmail.martsulgp.gympo.presentation.auth.login

import com.arellomobile.mvp.MvpView
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult

interface LoginView : MvpView {
    fun googleSignIn()
    fun progressBarVisibility(b: Boolean)
    fun onConnectionFailed(connectionResult: ConnectionResult)
    fun handleSignInResult(result: GoogleSignInResult)
    fun logger(message: String, debugLevel: LoginPresenter.DebugLevel)
    fun updateFbUser()
    fun showAlertDialog(message: String?)
    fun goToMainMenu()
}
