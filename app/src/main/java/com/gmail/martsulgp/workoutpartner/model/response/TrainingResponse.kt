package com.gmail.martsulgp.workoutpartner.model.response

import com.google.gson.annotations.SerializedName

data class TrainingResponse(
        val complexity: Float?,
        val trainingName: String?,
        val weekday: Int?,
        val time: Long?,
        val updated: Long?,
        val created: Long?,
        val ownerId: String?,
        val objectId: String?,
        @SerializedName("exercise")
        val exercises: List<ExerciseResponse>?
)