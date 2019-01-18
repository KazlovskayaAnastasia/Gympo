package com.gmail.martsulgp.gympo.presentation.menu.profileMenu

import com.arellomobile.mvp.MvpView
import com.gmail.martsulgp.gympo.data.model.entity.UserData

interface ProfileView : MvpView{
    fun updateData(userData: UserData)
}