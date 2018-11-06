package com.gmail.martsulgp.gympo.presentation

import com.arellomobile.mvp.MvpView

interface MainView : MvpView {

    fun showProgressBar()
    fun hideProgressBar()
    fun logger(message : String, debugLevel : MainPresenter.DebugLevel)

}