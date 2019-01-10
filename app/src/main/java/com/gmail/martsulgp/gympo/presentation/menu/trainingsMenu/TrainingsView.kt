package com.gmail.martsulgp.gympo.presentation.menu.trainingsMenu

import com.arellomobile.mvp.MvpView
import com.gmail.martsulgp.gympo.data.model.entity.TrainingsFeed

interface TrainingsView : MvpView {
    fun viewTrainings(listTrainings: List<TrainingsFeed>)
}