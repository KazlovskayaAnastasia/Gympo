package com.gmail.martsulgp.workoutpartner.presentation.registry

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.gmail.martsulgp.workoutpartner.data.UserDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter(private val userDataRepository: UserDataRepository) : MvpPresenter<MvpView>() {

    fun onPageLoaded() {
        userDataRepository.setLogInData("martsulg.p@gmail.com", "1111")

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { }
                .doOnTerminate { }
                .subscribe(
//                        { it -> viewState.logger(it.name ?: "", MainPresenter.DebugLevel.DEBUG) },
//                        { error -> viewState.logger(error.message ?: "", MainPresenter.DebugLevel.ERROR) }
                )
    }


}