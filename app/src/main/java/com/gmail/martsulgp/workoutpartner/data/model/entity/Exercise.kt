package com.gmail.martsulgp.workoutpartner.data.model.entity

data class Exercise(
        var exerciseName: String,
        var setsNum: Int,
        var repeatable: Boolean,
        var queuePos: Int,
        var ownerId: String,
        var objectId: String,
        var sets: List<Sets>
)