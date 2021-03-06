package com.gmail.martsulgp.gympo.presentation.menu.settingsMenu

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gmail.martsulgp.gympo.data.model.entity.UserDataObj
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import com.gmail.martsulgp.gympo.presentation.StartActivity.Companion.USER_ID
import com.gmail.martsulgp.gympo.presentation.StartActivity.Companion.USER_TOKEN
import com.gmail.martsulgp.gympo.presentation.StartActivity.Companion.pref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
@SuppressLint("CheckResult")
class SettingsPresenter(private val userDataRepository: UserDataRepository) : MvpPresenter<SettingsView>() {

    fun onLogOutPress(token: String){
        userDataRepository.logOut(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it: Void ->
                    pref.edit()
                            .remove(USER_TOKEN)
                            .remove(USER_ID)
                            .apply()
                    UserDataObj.clearData()
                    viewState.goToLoginScreen()
                }
    }
}