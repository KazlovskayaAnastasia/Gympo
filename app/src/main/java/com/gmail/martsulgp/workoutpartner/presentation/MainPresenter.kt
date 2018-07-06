package com.gmail.martsulgp.workoutpartner.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gmail.martsulgp.workoutpartner.data.UserDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class MainPresenter(val userDataRepository: UserDataRepository) : MvpPresenter<MainView>() {

    fun onPageLoaded(){
        userDataRepository.setLogInData("martsulg.p@gmail.com", "1111")

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { }
                .doOnTerminate { }
                .subscribe(
                        { it -> viewState.logger(it.name ?: "", DebugLevel.DEBUG) },
                        { error -> viewState.logger(error.message ?: "", DebugLevel.ERROR) }
                )
    }

    enum class DebugLevel(){
        DEBUG,
        ERROR
    }
}