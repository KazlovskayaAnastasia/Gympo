package com.gmail.martsulgp.gympo.presentation.auth.login

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult

@StateStrategyType(AddToEndSingleStrategy::class)
interface LoginView : MvpView {
    fun googleSignIn()
    fun progressBarVisibility(b: Boolean)
    fun onConnectionFailed(connectionResult: ConnectionResult)
    fun handleSignInResult(result: GoogleSignInResult)
    fun logger(message: String, debugLevel: LoginPresenter.DebugLevel)
    fun updateFbUser()

    @StateStrategyType(SkipStrategy::class)
    fun showAlertDialog(message: String?)

    fun goToMainMenu()
}
