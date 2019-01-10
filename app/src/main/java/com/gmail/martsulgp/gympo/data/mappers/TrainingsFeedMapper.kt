package com.gmail.martsulgp.gympo.data.mappers

import com.gmail.martsulgp.gympo.data.model.entity.TrainingsFeed
import com.gmail.martsulgp.gympo.data.model.response.TrainingResponse

object TrainingsFeedMapper {
    fun map(trainingResponse: TrainingResponse?) = TrainingsFeed(
            complexity = trainingResponse?.complexity ?: 0f,
            trainingName = trainingResponse?.trainingName ?: "",
            weekday = trainingResponse?.weekday ?: 1,
            time = trainingResponse?.time ?: 0L,
            updated = trainingResponse?.updated ?: 0L,
            created = trainingResponse?.created ?: 0L,
            ownerId = trainingResponse?.ownerId ?: "OwnerId not defined",
            objectId = trainingResponse?.objectId ?: "ObjectId not defined",
            exercises = ExercisesFeedMapper.map(trainingResponse?.exercises)
    )

    fun map(listTrainingResponse: List<TrainingResponse>?) : List<TrainingsFeed> = listTrainingResponse?.map { map(it) }?.toList() ?: listOf()
}