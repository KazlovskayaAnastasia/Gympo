package com.gmail.martsulgp.gympo.data.mappers

import com.gmail.martsulgp.gympo.data.model.entity.ExercisesFeed
import com.gmail.martsulgp.gympo.data.model.response.ExerciseResponse

object ExercisesFeedMapper {
    fun map(exerciseResponse: ExerciseResponse?) = ExercisesFeed(
            exerciseName = exerciseResponse?.exerciseName ?: "",
            setsNum = exerciseResponse?.setsNum ?: 0,
            isRepeatable = exerciseResponse?.repeatable ?: false,
            queuePos = exerciseResponse?.queuePos ?: -1,
            ownerId = exerciseResponse?.ownerId ?: "OwnerId not defined",
            objectId = exerciseResponse?.objectId ?: "ObjectId not defined",
            sets = SetsFeedMapper.map(exerciseResponse?.sets)
    )

    fun map(listExerciseResponse: List<ExerciseResponse>?) : List<ExercisesFeed> = listExerciseResponse?.map { map(it) }?.toList() ?: listOf()
}