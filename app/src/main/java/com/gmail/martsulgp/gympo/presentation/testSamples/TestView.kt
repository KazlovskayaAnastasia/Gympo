package com.gmail.martsulgp.gympo.presentation.testSamples

import com.arellomobile.mvp.MvpView

interface TestView : MvpView {

    fun showProgressBar()
    fun hideProgressBar()
    fun logger(message : String, debugLevel : TestPresenter.DebugLevel)
}