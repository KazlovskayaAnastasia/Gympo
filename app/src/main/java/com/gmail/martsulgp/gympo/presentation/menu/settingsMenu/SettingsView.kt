package com.gmail.martsulgp.gympo.presentation.menu.settingsMenu

import com.arellomobile.mvp.MvpView

interface SettingsView : MvpView {
    fun logOut()
    fun goToLoginScreen()
}