package com.gmail.martsulgp.workoutpartner.presentation.registry

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gmail.martsulgp.workoutpartner.data.model.response.UserInfoResponse
import com.gmail.martsulgp.workoutpartner.data.repository.UserDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

@InjectViewState
class RegistryPresenter(private val userDataRepository: UserDataRepository) : MvpPresenter<RegistryView>() {

    @SuppressLint("CheckResult")
    fun onPageLoaded() {
        userDataRepository.setLogInData("martsulg.p@gmail.com", "1111")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.progressBarVisibility(true) }
                .doOnTerminate { viewState.progressBarVisibility(false) }
                .subscribe(
                        { it: UserInfoResponse ->
                            viewState.logger(it.name ?: "", RegistryPresenter.DebugLevel.DEBUG)
                        },
                        { _ ->
                            Log.e("AAAAAAAError", "Errror")
                        } //viewState.logger(error.message ?: "", RegistryPresenter.DebugLevel.ERROR)// }
                )
    }

    enum class DebugLevel {
        DEBUG,
        ERROR
    }
}