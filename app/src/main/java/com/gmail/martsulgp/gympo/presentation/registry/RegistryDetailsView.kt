package com.gmail.martsulgp.gympo.presentation.registry

import com.arellomobile.mvp.MvpView

interface RegistryDetailsView : MvpView {

    fun progressBarVisibility(b: Boolean)
    fun showAlertDialog(message: String?)
    fun onSaveClick()
}