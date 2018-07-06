package com.gmail.martsulgp.workoutpartner.presentation

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {

    fun showProgressBar()
    fun hideProgressBar()
    fun logger(message : String, debugLevel : MainPresenter.DebugLevel)

}