package com.gmail.martsulgp.gympo.presentation.menu.profileMenu

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gmail.martsulgp.gympo.data.model.entity.UserDataObj
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class ProfilePresenter(private val userDataRepository: UserDataRepository) : MvpPresenter<ProfileView>() {

    @SuppressLint("CheckResult")
    fun getUserInfo() {
        userDataRepository.getUserInfo(UserDataObj.objectId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { it -> viewState.updateData(it)},
                        { error -> Log.d("GetUserInfo", "Completable onError: ${error.message.toString()}") }
                )
    }

}