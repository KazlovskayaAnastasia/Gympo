package com.gmail.martsulgp.gympo.presentation.registry

import com.arellomobile.mvp.MvpView


interface RegistryView : MvpView {

    fun progressBarVisibility(b: Boolean)
    fun logger(message : String, debugLevel : RegistryPresenter.DebugLevel)
}
