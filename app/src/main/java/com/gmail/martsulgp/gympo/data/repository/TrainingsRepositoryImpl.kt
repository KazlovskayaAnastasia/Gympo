package com.gmail.martsulgp.gympo.data.repository

import com.gmail.martsulgp.gympo.data.mappers.TrainingsFeedMapper
import com.gmail.martsulgp.gympo.data.model.entity.TrainingsFeed
import com.gmail.martsulgp.gympo.data.model.entity.UserDataObj
import com.gmail.martsulgp.gympo.data.service.BackendlessApi
import io.reactivex.Observable

class TrainingsRepositoryImpl : TrainingsRepository {
    private val api by lazy { BackendlessApi.create() }
    private val currentUser = UserDataObj

    override fun downloadTrainings() : Observable<List<TrainingsFeed>> {
        val url = StringBuilder("data/Timetable?where=ownerId%20%3D%20'")
        url.append(currentUser.ownerId)
        url.append("'&sortBy=weekday%20asc")
        return api.getTrainings(url.toString()).map { TrainingsFeedMapper.map(listTrainingResponse =  it) }
    }
}