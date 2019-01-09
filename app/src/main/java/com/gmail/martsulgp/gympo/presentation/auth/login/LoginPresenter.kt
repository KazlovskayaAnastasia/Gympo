package com.gmail.martsulgp.gympo.presentation.auth.login

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.facebook.Profile
import com.gmail.martsulgp.gympo.data.model.entity.UserData
import com.gmail.martsulgp.gympo.data.model.entity.UserDataObj
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import com.gmail.martsulgp.gympo.presentation.StartActivity.Companion.USER_ID
import com.gmail.martsulgp.gympo.presentation.StartActivity.Companion.USER_TOKEN
import com.gmail.martsulgp.gympo.presentation.StartActivity.Companion.pref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
@SuppressLint("CheckResult")
class LoginPresenter(private val userDataRepository: UserDataRepository) : MvpPresenter<LoginView>() {

    fun onLoginPress(email: String, password: String) {
        userDataRepository.logInUser(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.progressBarVisibility(true) }
                .doOnTerminate { viewState.progressBarVisibility(false) }
                .subscribe(
                        { it: UserData ->
                            pref.edit()
                                    .putString(USER_TOKEN, it.token)
                                    .putString(USER_ID, it.objectId)
                                    .apply()
                            UserDataObj.setData(it)
                            viewState.goToMainMenu()
//                            viewState.logger(UserDataObj.email ?: "", LoginPresenter.DebugLevel.DEBUG)
//                            viewState.logger(UserDataObj.toString(), LoginPresenter.DebugLevel.DEBUG)
                        },
                        { _ ->
                            viewState.showAlertDialog("Invalid login or password! Try again or register in application")
                            Log.e("Login data", "Error in login")
                        }
                )
    }

    fun exchangeWithBackendless(token: String) {
        userDataRepository.loginWithFB(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.progressBarVisibility(true) }
                .subscribe(
                        { user ->
                            viewState.updateFbUser()
                            viewState.logger(user.email, DebugLevel.DEBUG)
                        },
                        { error ->
                            viewState.logger(error.message ?: "", LoginPresenter.DebugLevel.ERROR)
                        }
                )
        viewState.progressBarVisibility(false)
    }

    fun updateUserData(fbProfile: Profile) {
        viewState.logger(fbProfile.name, DebugLevel.DEBUG)
    }


    enum class DebugLevel {
        DEBUG,
        ERROR
    }
}