package com.gmail.martsulgp.gympo.presentation.menu.trainingsMenu

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.gmail.martsulgp.gympo.data.repository.TrainingsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
@SuppressLint("CheckResult")
class TrainingsPresenter(private val trainingsRepository: TrainingsRepository) : MvpPresenter<TrainingsView>() {

    fun downloadTrainings(){
        trainingsRepository.downloadTrainings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{}
                .doOnTerminate{}
                .subscribe(
                        {it -> viewState.viewTrainings(it)},
                        { error -> Log.e("Trainings Presenter : ", error.toString())}
                )
//        viewState.viewTrainings(it)
    }
}