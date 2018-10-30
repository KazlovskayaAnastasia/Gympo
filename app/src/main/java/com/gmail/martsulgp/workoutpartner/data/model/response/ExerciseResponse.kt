package com.gmail.martsulgp.workoutpartner.data.model.response

data class ExerciseResponse(
        var exerciseName: String?,
        var setsNum: Int?,
        var repeatable: Boolean?,
        var queuePos: Int?,
        var ownerId: String?,
        var objectId: String?,
        var sets: List<SetsResponse>?
)