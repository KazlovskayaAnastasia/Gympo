package com.gmail.martsulgp.gympo.data.model.entity

data class ExercisesFeed (
    var exerciseName: String,
    var setsNum: Int,
    var isRepeatable: Boolean,
    var queuePos: Int,
    var ownerId: String,
    var objectId: String,
    var sets: List<SetsFeed>
)
