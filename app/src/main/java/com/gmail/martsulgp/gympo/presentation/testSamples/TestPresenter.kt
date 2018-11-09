package com.gmail.martsulgp.gympo.presentation.testSamples

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gmail.martsulgp.gympo.data.repository.UserDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class TestPresenter(val userDataRepository: UserDataRepository) : MvpPresenter<TestView>() {

    @SuppressLint("CheckResult")
    fun onPageLoaded(){
        userDataRepository.logInUser("martsulg.p@gmail.com", "1111")

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showProgressBar() }
                .doOnTerminate { viewState.hideProgressBar() }
                .subscribe(
                        { it -> viewState.logger(it.name ?: "NoName", DebugLevel.DEBUG) },
                        { error -> viewState.logger(error.message ?: "Error", DebugLevel.ERROR) }
                )
    }

    enum class DebugLevel{
        DEBUG,
        ERROR
    }
}