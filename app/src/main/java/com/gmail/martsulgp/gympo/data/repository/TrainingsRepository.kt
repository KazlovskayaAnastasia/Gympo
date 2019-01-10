package com.gmail.martsulgp.gympo.data.repository

import com.gmail.martsulgp.gympo.data.model.entity.TrainingsFeed
import io.reactivex.Observable

interface TrainingsRepository {
    fun downloadTrainings() : Observable<List<TrainingsFeed>>
}